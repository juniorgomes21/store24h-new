package br.apc.smsdriver.api.repositories;

import br.apc.smsdriver.entities.ModemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModemRepository extends JpaRepository<ModemModel, Long> {
}
