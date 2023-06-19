package br.com.store24h.store24h.repository;

import br.com.store24h.store24h.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdmDbRepository extends JpaRepository<Administrador, Long> {

    Optional<Administrador> findByEmail(String email);
}
