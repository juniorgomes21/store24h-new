package br.com.apc.chef.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apc.chef.entities.Board;
import br.com.apc.chef.repositories.BoardRepository;

@RestController
@RequestMapping("board")
public class BoardResource {
	
	@Autowired
	private BoardRepository boardRepository;
	
	
	@GetMapping
	public ResponseEntity<List<Board>> findAll(){
		return ResponseEntity.ok().body(boardRepository.findAll()); 
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Board> findById(@PathVariable Long id){
		
		return ResponseEntity.ok().body(boardRepository.findById(id).get());
	}
	
}
