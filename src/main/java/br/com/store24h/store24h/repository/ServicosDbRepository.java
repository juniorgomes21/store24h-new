package br.com.store24h.store24h.repository;

import br.com.store24h.store24h.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicosDbRepository extends JpaRepository<Servico, Long> {
}
