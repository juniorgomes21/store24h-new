package br.com.store24h.store24h.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.store24h.store24h.model.Activation;

public interface ActivationRepository extends JpaRepository<Activation, Long> {

}
