Тестовое задание для комании OneVizion на позицию Java Developer

Предполагается, что существует база данных с таблицей book:
create table book (
id number not null,
title varchar2(150) not null,
author varchar2(150) not null,
description varchar2(150),
constraint book_pk primary key (id)
);
Тестовые данные:
insert into BOOK (id, title, author, description)
values (1, 'Crime and Punishment', 'F. Dostoevsky', null);
insert into BOOK (id, title, author, description)
values (2, 'Anna Karenina', 'L. Tolstoy', null);
insert into BOOK (id, title, author, description)
values (3, 'The Brothers Karamazov', 'F. Dostoevsky', null);
insert into BOOK (id, title, author, description)
values (4, 'War and Peace', 'L. Tolstoy', null);
insert into BOOK (id, title, author, description)
values (5, 'Dead Souls', 'N. Gogol', null);
commit;
Необходимо реализовать контроллер с 4мя endpoints:
1. endpoint возвращающий список все книг, которые содержатся в таблице book,
отсортированный в обратном алфавитном порядке значения колонки book.title
2. endpoint добавления новой книги в таблицу book
3. endpoint возвращающий список всех книг, сгруппированных по автору
книги(book.author)
4. endpoint принимающий в качестве параметра символ и возвращающий список из 10 авторов,
в названии книг которых этот символ встречается наибольшее количество раз вместе с
количеством вхождений этого символа во все названия книг автора.
Регистр символа не имеет значения. Авторы, в названии книг которых символ отсутствует, не
должны присутствовать в возвращаемом значении.
Пример:
Входной параметр: “а”
Результат: [{L. Tolstoy, 7},{F. Dostoevsky, 4},{N. Gogol, 1}]
Требования к коду проекта:
1. для реализации необходимо использовать Spring Framework.
2. endpoints должны соответствовать RESTful Resource Naming Convention.
3. endpoints должны получать\возвращать JSON объекты.
4. для доступа к базе данных необходимо использовать JdbcTemplate из Spring Framework.
5.в одном из endpoint обязательно применить Java Stream API.
6.готовый проект залить в GitHub.
