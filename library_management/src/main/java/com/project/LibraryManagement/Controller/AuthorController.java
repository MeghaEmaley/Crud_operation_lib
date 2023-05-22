package com.project.LibraryManagement.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.project.LibraryManagement.Entity.Author;
import com.project.LibraryManagement.Service.AuthorService;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        return ResponseEntity.ok(authors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable Long id) {
        Author author = authorService.getAuthorById(id);
        if (author != null) {
            return ResponseEntity.ok(author);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found");
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> createAuthor(@RequestBody Author authorRequest) {
        Author author = authorService.createAuthor(authorRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(author);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateAuthor(@PathVariable Long id, @RequestBody Author authorRequest) {
        Author author = authorService.updateAuthor(id, authorRequest);
        if (author != null) {
            return ResponseEntity.ok(author);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found");
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long id) {
        boolean deleted = authorService.deleteAuthor(id);
        if (deleted) {
            return ResponseEntity.ok("Author deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found");
        }
    }
}
