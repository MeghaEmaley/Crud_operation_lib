package com.project.LibraryManagement.Service;

import org.springframework.stereotype.Service;

import com.project.LibraryManagement.Entity.Book;
import com.project.LibraryManagement.Repository.BookRepository;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book ID: " + id));
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book bookDetails) {
        Book book = getBookById(id);
        book.setName(bookDetails.getName());
        book.setAuthor(bookDetails.getAuthor());
        
        return bookRepository.save(book);
    }

	public boolean deleteBook(Long id) {
		 bookRepository.deleteById(id);
		return false;
	}

    
}