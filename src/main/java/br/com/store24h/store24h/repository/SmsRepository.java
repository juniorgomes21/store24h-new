package br.com.store24h.store24h.repository;

import br.com.store24h.store24h.model.SmsModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SmsRepository extends JpaRepository<SmsModel, Long>{
    Optional<List<SmsModel>> findByChipnumber(String chipnumber);
    Optional<SmsModel> findByChipnumberAndIdActivation(String chipnumber, Long idActivation);
    Optional<SmsModel> findByIdActivation(Long idActivation, Sort sort);
    Optional<SmsModel> findFirstByIdActivationOrderByDateDesc(Long idActivation);
    SmsModel findByMsgAndIdActivation(String msg, Long idActivation);
    Optional<SmsModel> findByDateAfterAndIdActivation(LocalDateTime time, Long id);
    Optional<List<SmsModel>> findByChipnumber(String chipnumber, Pageable pageable);
    Optional<List<SmsModel>> findByMsgContaining(String parteNome);
    Optional<List<SmsModel>> findByDate(LocalDateTime date);
    Optional<List<SmsModel>> findByDateAfter(LocalDateTime date);
    Optional<List<SmsModel>> findByDateBetween(LocalDateTime start, LocalDateTime end);
    Optional<List<SmsModel>> findByDateBetweenAndChipnumber(LocalDateTime start, LocalDateTime end, String chipnumber);

    Optional<SmsModel> findByDateGreaterThanAndIdActivation(LocalDateTime date, Long idActivation);
    void deleteByChipnumber(String chipNumber);
    void deleteByIdActivation(Long id);
}
