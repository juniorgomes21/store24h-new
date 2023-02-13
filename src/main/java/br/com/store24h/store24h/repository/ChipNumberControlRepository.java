package br.com.store24h.store24h.repository;

import br.com.store24h.store24h.model.ChipNumberControl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChipNumberControlRepository extends JpaRepository<ChipNumberControl, Long> {
    Optional<ChipNumberControl> findByChipNumber(String chipNumber);
}
