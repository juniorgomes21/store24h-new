package br.com.store24h.store24h.repository;

import br.com.store24h.store24h.model.ChipModel;
import br.com.store24h.store24h.model.ChipNumberControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChipNumberControlRepository extends JpaRepository<ChipNumberControl, Long> {
    List<ChipNumberControl> findByChipNumberIn(List<String> chipModelList);
    Optional<ChipNumberControl> findByChipNumber(String chipNumber);
    Optional<ChipNumberControl> findByChipNumberAndAliasService(String chipNumber, String aliasService);
}
