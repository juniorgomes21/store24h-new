package br.com.store24h.store24h.repository;

import br.com.store24h.store24h.model.CompraServiso;
import br.com.store24h.store24h.services.CompraService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyServiceRepository extends JpaRepository<CompraServiso, Long> {

    CompraServiso findByIdActivation(Long id);
}
