package br.com.store24h.store24h.repository;

import br.com.store24h.store24h.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaReppository extends JpaRepository<Conta, Long> {

}
