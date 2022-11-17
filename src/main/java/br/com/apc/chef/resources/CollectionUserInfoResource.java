package br.com.apc.chef.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apc.chef.entities.CollectionUserInfo;
import br.com.apc.chef.repositories.CollectionUserInfoRepository;



@RestController
@RequestMapping(value="collection_user_info")
public class CollectionUserInfoResource {
	
	@Autowired
	CollectionUserInfoRepository collectionUserInfoRepository;
	
	@GetMapping
	public ResponseEntity<List<CollectionUserInfo>> findAll(){
		
		return ResponseEntity.ok().body(collectionUserInfoRepository.findAll());
		
	}
}
