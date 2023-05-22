package com.project.LibraryManagement.Service;


import org.springframework.stereotype.Service;
import com.project.LibraryManagement.Entity.Author;
import com.project.LibraryManagement.Repository.AuthorRepository;

import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    public Author createAuthor(Author authorRequest) {
        Author author = new Author();
        author.setName(authorRequest.getName());
        return authorRepository.save(author);
    }

    public Author updateAuthor(Long id, Author authorRequest) {
        Author author = authorRepository.findById(id).orElse(null);
        if (author != null) {
            author.setName(authorRequest.getName());
            return authorRepository.save(author);
        }
        return null;
    }

    public boolean deleteAuthor(Long id) {
        Author author = authorRepository.findById(id).orElse(null);
        if (author != null) {
            authorRepository.delete(author);
            return true;
        }
        return false;
    }
}
