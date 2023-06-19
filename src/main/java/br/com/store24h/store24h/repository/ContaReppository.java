package br.com.store24h.store24h.repository;

import br.com.store24h.store24h.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaReppository extends JpaRepository<Conta, Long> {

}
