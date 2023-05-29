package br.com.store24h.store24h.repository;

import br.com.store24h.store24h.model.ChipOther;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChipOtherRepository extends JpaRepository<ChipOther, Long> {
    Optional<ChipOther> findByNumber(String chipNumber);

    void deleteByNumber(String chipNumber);
}
