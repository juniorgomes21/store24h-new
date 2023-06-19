package br.com.store24h.store24h.task;

import br.com.store24h.store24h.model.TimeZone;
import br.com.store24h.store24h.repository.ChipOtherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class ChipTask {

    @Autowired
    private ChipOtherRepository chipOtherRepository;

    @Scheduled(fixedRate = 10000) //1200000
    @Transactional
    public void deleteOuther() {
        LocalDateTime date = LocalDateTime.now(ZoneId.of(TimeZone.BR.getZone())).minusMinutes(21);

        chipOtherRepository.deleteByDateTimeBefore(date);
    }
}
