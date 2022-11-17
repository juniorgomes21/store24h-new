package br.com.apc.chef.resources;

import java.util.List;

import br.com.apc.chef.dtos.CollectionDto;
import br.com.apc.chef.dtos.UserDto;
import br.com.apc.chef.entities.CollectionUserInfo;
import br.com.apc.chef.entities.User;
import br.com.apc.chef.service.CollectionAdminManager;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.apc.chef.entities.Collection;
import br.com.apc.chef.repositories.CollectionRepository;

import javax.validation.Valid;

@RestController
@RequestMapping("/collection")
public class CollectionResource {

	@Autowired
	private CollectionRepository collectionRepository;
	
	@GetMapping
	public ResponseEntity<List<Collection>> findAll(){
		return ResponseEntity.ok().body(collectionRepository.findAll()); 
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Collection> findById(@PathVariable Long id){
		return ResponseEntity.ok().body(collectionRepository.findById(id).get());
	}

	@PostMapping
	public ResponseEntity<Long> create(@RequestBody @Valid CollectionDto col) {
		Collection lol = CollectionAdminManager.create(col);
		return ResponseEntity.ok().body(lol.getId());
	}

}
