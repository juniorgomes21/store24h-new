package br.com.store24h.store24h.repository;

import br.com.store24h.store24h.model.CompraServiso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface BuyServiceRepository extends JpaRepository<CompraServiso, Long> {

    CompraServiso findByIdActivation(Long id);

    Page<CompraServiso> findByLocalDateTimeBetween(LocalDateTime dateInitial , LocalDateTime datefinal , Pageable pageable);
}
