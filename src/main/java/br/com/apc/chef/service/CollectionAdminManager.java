package br.com.apc.chef.service;

import br.com.apc.chef.entities.Administrador;
import br.com.apc.chef.entities.CollectionUserInfo;
import br.com.apc.chef.entities.User;
import br.com.apc.chef.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apc.chef.dtos.CollectionDto;
import br.com.apc.chef.entities.Collection;
import br.com.apc.chef.repositories.CollectionRepository;

@Service
public class CollectionAdminManager {

	@Autowired
	private static CollectionRepository collectionRepository;
	@Autowired
	private static UserRepository userRepository;

//	@private

	public static Collection create(CollectionDto dto) {
		User u = userRepository.getById(1L);
		if (!(u instanceof Administrador)) return null;
		Collection cole = new Collection();
		BeanUtils.copyProperties(cole, dto);
		CollectionUserInfo collectionUserInfo = new CollectionUserInfo();
		cole.addCollectionUserInfo(collectionUserInfo);
		collectionUserInfo.setUser(u);
		return collectionRepository.save(cole);
	}
}
