package br.com.apc.chef.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apc.chef.entities.Part;
import br.com.apc.chef.repositories.PartRepository;

@RestController
@RequestMapping("part")
public class PartResource {
	
	@Autowired
	private PartRepository partRepository;	
	
	@GetMapping
	public ResponseEntity<List<Part>> findAll(){
		return ResponseEntity.ok().body(partRepository.findAll()); 
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Part> findById(@PathVariable Long id){
		
		return ResponseEntity.ok().body(partRepository.findById(id).get());
	}
	
}
