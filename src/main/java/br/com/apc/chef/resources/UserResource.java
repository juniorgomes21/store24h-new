package br.com.apc.chef.resources;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apc.chef.dtos.UserDto;
import br.com.apc.chef.entities.User;
import br.com.apc.chef.repositories.UserRepository;

@RestController
@RequestMapping(value = "users")
public class UserResource {
//	static List<User> list =  new ArrayList<>();
	
	@Autowired
	UserRepository repository;
	
	@GetMapping
	public ResponseEntity<Collection<User>> findAll(){
//		List<User> list =  new ArrayList<>();
//		list.add(new User(1L, "Fernando", "91998317849", "brainux@gmail.com" , "123456"));
//		list.add(new User(2L, "Antonio" , "91998317848", "brainux2@gmail.com", "123456"));
//		list.add(new User(3L, "Pantoja" , "91998317847", "brainux3@gmail.com", "123456"));
//		list = repository.findAll();
		return ResponseEntity.ok().body(repository.findAll());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(repository.findById(id).get());
	}
	
	@PostMapping
	public ResponseEntity<User> create(@RequestBody @Valid UserDto user) {
		User u = new User();
		BeanUtils.copyProperties(user, u);
		return ResponseEntity.ok().body(repository.save(u));
	}
	
}
