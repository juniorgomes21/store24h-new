package br.com.store24h.store24h.task;

import br.com.store24h.store24h.model.SmsModel;
import br.com.store24h.store24h.model.TimeZone;
import br.com.store24h.store24h.repository.SmsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Component
public class SmsModelTask {

    @Autowired
    private SmsRepository smsRepository;

    @Transactional
    @Scheduled(cron = "0 0 * * * *")
    public void deleteSmsModel() {
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of(TimeZone.BR.getZone()));
        LocalDateTime dateBefore = localDateTime.minusDays(1);

        List<SmsModel> smsModelList = smsRepository.findByDateBefore(dateBefore);

        smsRepository.deleteAll(smsModelList);
    }
}
