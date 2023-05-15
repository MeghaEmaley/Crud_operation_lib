package com.project.repository;
import org.springframework.data.repository.CrudRepository;

import com.project.bean.Books;
public interface BooksRepository extends CrudRepository<Books, Integer>
{
}
