package br.com.store24h.store24h.repository;

import br.com.store24h.store24h.model.PaisOperadoras;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaisOperadorasDbRepository extends JpaRepository<PaisOperadoras, Long> {

    Optional<PaisOperadoras> findByPais(String nomePais);
}
