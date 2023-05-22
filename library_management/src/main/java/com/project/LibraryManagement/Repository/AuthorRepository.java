package com.project.LibraryManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.LibraryManagement.Entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
