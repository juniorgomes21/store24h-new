package br.apc.smsdriver.api.repositories;

import br.apc.smsdriver.entities.SmsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsRepository extends JpaRepository<SmsModel, Long> {
}
