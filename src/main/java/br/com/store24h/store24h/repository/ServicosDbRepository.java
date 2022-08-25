package br.com.store24h.store24h.repository;

import br.com.store24h.store24h.model.Servicos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicosDbRepository extends JpaRepository<Servicos, Long> {
}
