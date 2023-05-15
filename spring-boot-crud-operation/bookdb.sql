CREATE DATABASE IF NOT EXISTS BookDB;
USE BookDB;

drop table if exists Book;
CREATE TABLE Book(
book_id BIGINT NOT NULL AUTO_INCREMENT,
book_name VARCHAR(50) DEFAULT NULL,
bookborrow VARCHAR(50) DEFAULT NULL,
bookreturn VARCHAR(50) DEFAULT NULL,

PRIMARY KEY (book_id));

USE BookDB ;
SELECT * FROM book;