package com.books.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.books.apirest.models.Registry;

public interface RegistryRepository extends JpaRepository<Registry, Long>{

	Registry findById(long id);
	Registry findByBookId(long id);
}
