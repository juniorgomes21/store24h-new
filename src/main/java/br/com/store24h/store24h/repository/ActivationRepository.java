package br.com.store24h.store24h.repository;

import br.com.store24h.store24h.services.core.ActivationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.store24h.store24h.model.Activation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ActivationRepository extends JpaRepository<Activation, Long> {

    Optional<Activation> findById(Long id);
    Optional<Activation> findByChipNumberAndAliasService(String chipNumber, String aliasService);
    List<Activation> findByChipNumber(String chipNumber);
    List<Activation> findByStatusBuz(ActivationStatus activationStatus);
    List<Activation> findAllByStatusBuzAndChipNumber(ActivationStatus activationStatus, String chipNumber);
    List<Activation> findByStatusBuzAndChipNumberAndAliasService(ActivationStatus activationStatus, String chipNumber, String aliasService);
    List<Activation> findByStatusAndInitialTimeBefore(int i, LocalDateTime currentTimeMinus20Minutes);
}
