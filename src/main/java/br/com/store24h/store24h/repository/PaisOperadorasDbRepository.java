package br.com.store24h.store24h.repository;

import br.com.store24h.store24h.model.PaisOperadoras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaisOperadorasDbRepository extends JpaRepository<PaisOperadoras, Long> {

    Optional<List<PaisOperadoras>> findByPais(String nomePais);
}
