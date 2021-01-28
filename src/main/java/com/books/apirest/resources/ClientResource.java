package com.books.apirest.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.books.apirest.models.Client;
import com.books.apirest.repository.ClientRepository;

@RestController
@RequestMapping(value="/api")
public class ClientResource {
	@Autowired
	ClientRepository clientRepository;
	
	@GetMapping("/clients")
	public List<Client> listClient(){
		return clientRepository.findAll();
	}
	
	@GetMapping("/client/{id}")
	public Client retrieveClient(@PathVariable(value="id") long id){
		return clientRepository.findById(id);
	}

	@PostMapping("/client")
	public Client createClient(@RequestBody Client client) {
		return clientRepository.save(client);
	}
	
	@DeleteMapping("/client")
	public void deleteBook(@RequestBody Client client) {
		clientRepository.delete(client);
	}
	
	@PutMapping("/client/{id}")
	public Client updateClient(@RequestBody Client client) {
		return clientRepository.save(client);
	}
}
