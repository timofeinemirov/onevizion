package com.onevision.job;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onevision.job.model.Book;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class BookControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenCreateBook_thenStatus200() throws Exception {

        Book book = new Book();
        book.setId(100);
        book.setTitle("Roadside Picnic");
        book.setAuthor("Arkady Strugatsky");
        book.setDescription("Red Schuhart is a stalker, one of those young rebels who are compelled...");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(book);

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk());
    }

    @Test
    void givenBooks_whenGetBooks_thenStatus200() throws Exception {
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk());
    }

    @Test
    void findAllGrouped() {

    }

    @Test
    void searchByTitle() {

    }

}
