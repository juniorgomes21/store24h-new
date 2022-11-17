package br.apc.smsdriver.api.repositories;

import br.apc.smsdriver.entities.ChipModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChipRepository extends JpaRepository<ChipModel, Long> {
}
