package com.books.apirest.resources;

import java.text.SimpleDateFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.books.apirest.models.Registry;
import com.books.apirest.repository.RegistryRepository;

@RestController
@RequestMapping(value="/api")
public class RegistryResource {

	final int tax = 2;
	SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/DD");
	
	public Registry deliveryBook(Registry registry) {
		
			registry.setReserved(false);
			
			long lateDays = (registry.getDelivery_date().getTime() - registry.getAllocated_date().getTime()) / (1000*60*60*24) - 3;
			
			if(lateDays >=1 && lateDays <=3) {
				double finalTax = tax + (tax * 0.03) + (lateDays * 0.002);
				registry.setTax(true);
				registry.setValue_tax(finalTax);
			}else if(lateDays >=3 && lateDays <=5){
				double finalTax = tax + (tax * 0.03) + (lateDays * 0.004);
				registry.setTax(true);
				registry.setValue_tax(finalTax);
			}else if(lateDays >5){
				double finalTax = tax + (tax * 0.03) + (lateDays * 0.006);
				registry.setTax(true);
				registry.setValue_tax(finalTax);
			}else {
				registry.setTax(false);
				registry.setValue_tax(tax);
			}
		return registry;
	}
	
	@Autowired
	RegistryRepository registryRepository;
	
	@GetMapping("/registrys")
	public List<Registry> listRegistry(){
		return registryRepository.findAll();
	}
	
	@GetMapping("/registry/{id}")
	public Registry retrieveRegistry(@PathVariable(value="id") long id){
		return registryRepository.findById(id);
	}

	@PostMapping("/registry")
	public ResponseEntity<String> createRegistry(@RequestBody Registry registry) {
		
		long book_id = registry.getBook().getId();
		Registry registrByBookId = registryRepository.findByBookId(book_id);
		
		if(!registrByBookId.isReserved()) {
			registryRepository.save(registry);
			return new ResponseEntity<String>("Reervado com sucesso",HttpStatus.OK);
		}
		String errorMessage = "Livro - " + registry.getBook().getName() + " - está indisponível no momento";
		return new ResponseEntity <String>(errorMessage,HttpStatus.BAD_REQUEST);
	}
	

	@DeleteMapping("/registry")
	public void deleteRegistry(@RequestBody Registry registry) {
		registryRepository.delete(registry);
	}
	
	@PutMapping("/registry/{id}")
	public Registry updateRegistry(@RequestBody Registry registry) {
		
		if(registry.getAllocated_date()!=null && 
		   registry.getDelivery_date()!=null &&
		   registry.isReserved() == true
		) {
			this.deliveryBook(registry);
		}	
		
		return registryRepository.save(registry);
	}
	
	@PatchMapping("/registry/{id}")
	public Registry partialUpdateRegistry(@RequestBody Registry registry){
		
		if(registry.getAllocated_date()!=null && 
		   registry.getDelivery_date()!=null &&
		   registry.isReserved() == true
		) {
			this.deliveryBook(registry);
		}		
		return registryRepository.save(registry);
	}
	
}
