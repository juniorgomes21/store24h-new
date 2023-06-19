package br.com.store24h.store24h.repository;

import br.com.store24h.store24h.services.core.ActivationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.store24h.store24h.model.Activation;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ActivationRepository extends JpaRepository<Activation, Long> {

    Optional<Activation> findById(Long id);
    Optional<Activation> findByChipNumberAndAliasService(String chipNumber, String aliasService);
    List<Activation> findByChipNumber(String chipNumber);
    List<Activation> findByStatusBuz(ActivationStatus activationStatus);
    List<Activation> findAllByStatusBuzAndChipNumber(ActivationStatus activationStatus, String chipNumber);
    List<Activation> findByStatusBuzAndChipNumberAndAliasService(ActivationStatus activationStatus, String chipNumber, String aliasService);
    List<Activation> findByStatusInAndInitialTimeBefore(List<Integer> list, LocalDateTime currentTimeMinus20Minutes);
    List<Activation> findByStatusInAndInitialTimeAfter(List<Integer> list, LocalDateTime currentTimeMinus20Minutes);

    List<Activation> findByApiKeyAndInitialTimeAfterAndStatusNotIn(String apiKey, LocalDateTime date, List<Integer> status);

    List<Activation> findByApiKey(String apiKey);

    List<Activation> findAllByIdIn(List<Long> idActivations);
}
