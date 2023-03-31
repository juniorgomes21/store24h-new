package br.com.store24h.store24h.repository;

import br.com.store24h.store24h.model.SmsModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SmsRepository extends JpaRepository<SmsModel, Long>{
    Optional<SmsModel> findByChipnumberAndIdActivation(String chipnumber, Long idActivation);
    Optional<SmsModel> findByIdActivation(Long idActivation, Sort sort);
    Optional<SmsModel> findFirstByIdActivationOrderByDateDesc(Long idActivation);
    Optional<SmsModel> findFirstByChipnumberAndIdActivationOrderByDateDesc(String chipNumber, Long idActivation);
    SmsModel findByMsgAndIdActivation(String msg, Long idActivation);
    Optional<SmsModel> findByDateAfterAndIdActivation(LocalDateTime time, Long id);
    List<SmsModel> findByDateBefore(LocalDateTime dateBefore);
    Optional<SmsModel> findByDateGreaterThanAndIdActivation(LocalDateTime date, Long idActivation);
    void deleteByChipnumber(String chipNumber);
    void deleteByIdActivation(Long id);

}
