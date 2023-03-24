package br.com.store24h.store24h.task;

import br.com.store24h.store24h.model.Activation;
import br.com.store24h.store24h.model.TimeZone;
import br.com.store24h.store24h.repository.ActivationRepository;
import br.com.store24h.store24h.services.core.ActivationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Component
public class ActivationTask {

    @Autowired
    private ActivationRepository activationRepository;

    @Autowired
    private ActivationService activationService;

    @Scheduled(fixedRate = 40000)
    public void cancelActivations() {
        LocalDateTime currentTimeMinus20Minutes = LocalDateTime.now(ZoneId.of(TimeZone.BR.getZone())).minusMinutes(20);
        List<Activation> activations = activationRepository.findByStatusAndInitialTimeBefore(3, currentTimeMinus20Minutes);

        activations.forEach( activation -> {
            activationService.cancelActivation(activation.getId(), activation.getApiKey());
        });
    }
}
