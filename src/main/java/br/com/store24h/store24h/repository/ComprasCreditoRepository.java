package br.com.store24h.store24h.repository;

import br.com.store24h.store24h.model.ComprasCredito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComprasCreditoRepository extends JpaRepository<ComprasCredito, Long> {
}
