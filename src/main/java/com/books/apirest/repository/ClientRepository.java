package com.books.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.books.apirest.models.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

	Client findById(long id);
}
