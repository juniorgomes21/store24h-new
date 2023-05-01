package br.com.store24h.store24h.repository;

import br.com.store24h.store24h.model.CompraServiso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface BuyServiceRepository extends JpaRepository<CompraServiso, Long> {

    CompraServiso findByIdActivation(Long id);

    Page<CompraServiso> findByLocalDateTimeBetween(LocalDateTime dateInitial , LocalDateTime datefinal , Pageable pageable);
}
