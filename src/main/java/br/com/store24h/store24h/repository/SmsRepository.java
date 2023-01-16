package br.com.store24h.store24h.repository;

import br.com.store24h.store24h.model.SmsModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SmsRepository extends JpaRepository<SmsModel, Long>{
    Optional<List<SmsModel>> findByChipnumber(String chipnumber);
    Optional<List<SmsModel>> findByMsgContaining(String parteNome);
    Optional<List<SmsModel>> findByDateBetween(LocalDateTime start, LocalDateTime end);
    Optional<List<SmsModel>> findByDateBetweenAndChipnumber(LocalDateTime start, LocalDateTime end, String chipnumber);
}
