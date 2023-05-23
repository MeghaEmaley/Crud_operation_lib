package com.project.LibraryManagement;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import com.project.LibraryManagement.Entity.Author;
import com.project.LibraryManagement.Entity.Book;
import com.project.LibraryManagement.Entity.Role;
import com.project.LibraryManagement.Entity.User;
import com.project.LibraryManagement.Repository.UserRepository;
import com.project.LibraryManagement.Service.BookService;

@SpringBootApplication
public class LibraryManagementApplication {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private BookService bookService;

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementApplication.class, args);
	}

	@Bean
	public CommandLineRunner initialCreate() {
		return (args) -> {

			var book = new Book("AP1287", "Spring in Action " );
			book.addAuthors(new Author("Matt"));
			bookService.createBook(book);

			var book1 = new Book("BP567#R", "Spring Microservices");
			book1.addAuthors(new Author("Maxwell"));
			bookService.createBook(book1);

			var book2 = new Book("GH67F#", "Spring Boot");
			book2.addAuthors(new Author("Josh Lang"));
			bookService.createBook(book2);

			var user = new User("admin", "admin", "admin@admin.com", passwordEncoder.encode("Temp123"),
					Arrays.asList(new Role("ROLE_ADMIN")));
			userRepository.save(user);

		};
	}
}