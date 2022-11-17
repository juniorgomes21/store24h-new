package br.com.apc.chef;


import br.com.apc.chef.entities.Administrador;
import br.com.apc.chef.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import br.com.apc.chef.entities.Collection;
import br.com.apc.chef.entities.CollectionUserInfo;
import br.com.apc.chef.entities.User;
import br.com.apc.chef.repositories.CollectionRepository;
import br.com.apc.chef.repositories.CollectionUserInfoRepository;
import br.com.apc.chef.repositories.UserRepository;

//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
public class ChefMeloApplication implements CommandLineRunner {
	@Autowired
	AdminRepository adminRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CollectionUserInfoRepository collectionUserInfoRepository;
	@Autowired
	CollectionRepository collectionRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(ChefMeloApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Administrador admin = new Administrador("Fernando", "91998317841", "brainux0@gmail.com" , "123456");
////		adminRepository.save(admin);
//
//		User user1 = new User("Fernando", "91998317849", "brainux1@gmail.com" , "123456");
//		User user2 = new User("Antonio" , "91998317848", "brainux2@gmail.com", "123456");
//		User user3 = new User("Pantoja" , "91998317847", "brainux3@gmail.com", "123456");
//
//		Collection collection1 = new Collection(null);
//		collectionRepository.save(collection1);
//		Collection collection2 = new Collection(null);
//		collectionRepository.save(collection2);
//		Collection collection3 = new Collection(null);
//		collectionRepository.save(collection3);
//
//		CollectionUserInfo collectionUserInfo1 =  new CollectionUserInfo(user1, collection1);
//		CollectionUserInfo collectionUserInfo2 =  new CollectionUserInfo(user2, collection2);
//		CollectionUserInfo collectionUserInfo3 =  new CollectionUserInfo(user3, collection3);
//
//
//		collection1.getCollectionUserInfos().add(collectionUserInfo1);
//
//
//
////		userRepository.save(user1);
////		userRepository.save(user2);
////		userRepository.save(user3);
//
//		collectionUserInfoRepository.save(collectionUserInfo1);
//		collectionUserInfoRepository.save(collectionUserInfo2);
//		collectionUserInfoRepository.save(collectionUserInfo3);

		
	}

}
