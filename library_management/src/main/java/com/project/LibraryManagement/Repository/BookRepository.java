package com.project.LibraryManagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.project.LibraryManagement.Entity.*;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	 @Query("SELECT b FROM Book b WHERE b.author = :author")
	    List<Book> findByAuthor(Author author);

	    @Query("SELECT b FROM Book b WHERE b.title LIKE %:keyword%")
	    List<Book> searchByTitle(String keyword);
	    
	    @Query("SELECT b FROM Book b WHERE b.author.name = :authorName")
	    List<Book> findByAuthorName(String authorName);
}