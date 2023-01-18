package br.com.store24h.store24h.repository;

import br.com.store24h.store24h.model.Administrador;
import br.com.store24h.store24h.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDbRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByApiKey(String apiKey);


}
