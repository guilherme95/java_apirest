package com.books.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.books.apirest.models.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

	Book findById(long id);
	
}
