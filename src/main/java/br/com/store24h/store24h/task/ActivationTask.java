package br.com.store24h.store24h.task;

import br.com.store24h.store24h.model.Activation;
import br.com.store24h.store24h.model.SmsModel;
import br.com.store24h.store24h.model.TimeZone;
import br.com.store24h.store24h.repository.ActivationRepository;
import br.com.store24h.store24h.repository.SmsRepository;
import br.com.store24h.store24h.services.core.ActivationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class ActivationTask {

    @Autowired
    private ActivationRepository activationRepository;

    @Autowired
    private SmsRepository smsRepository;

    @Autowired
    private ActivationService activationService;

    @Scheduled(fixedRate = 40000)
    public void cancelActivations() {
        LocalDateTime currentTimeMinus20Minutes = LocalDateTime.now(ZoneId.of(TimeZone.BR.getZone())).minusMinutes(20);
        List<Integer> list = Arrays.asList(-1, 3);
        List<Activation> activations = activationRepository.findByStatusInAndInitialTimeBefore(list, currentTimeMinus20Minutes);

        activations.forEach( activation -> {
            if(activation.getSmsStringModels().isEmpty()) {
                activationService.cancelActivation(activation.getId(), activation.getApiKey());
            } else {
                activationService.conclude(activation.getId());
            }
        });
    }

    @Scheduled(fixedRate = 15000)
    public void verifySmsActivations() {
        LocalDateTime currentTimeMinus20Minutes = LocalDateTime.now(ZoneId.of(TimeZone.BR.getZone())).minusMinutes(20);
        List<Integer> list = Arrays.asList(-1);
        List<Activation> activations = activationRepository.findByStatusInAndInitialTimeAfter(list, currentTimeMinus20Minutes);

        if(!activations.isEmpty()) {
            List<Activation> activationList = new ArrayList<>();
            for(Activation a: activations) {
                Optional<SmsModel> smsModelOptional = smsRepository.findByChipnumberAndIdActivation(a.getChipNumber(), a.getId());
                if(smsModelOptional.isPresent()) {
                    SmsModel smsModel = smsModelOptional.get();
                    activationService.saveSms(a, smsModel.getMsg());
                    activationList.add(a);
                }
            }

            activationRepository.saveAll(activationList);
        }
    }

    @Scheduled(fixedRate = 15000)
    public void verifyRetrySmsActivations() {
        LocalDateTime currentTimeMinus20Minutes = LocalDateTime.now(ZoneId.of(TimeZone.BR.getZone())).minusMinutes(20);
        List<Integer> list = Arrays.asList(3);
        List<Activation> activations = activationRepository.findByStatusInAndInitialTimeAfter(list, currentTimeMinus20Minutes);

        if(!activations.isEmpty()) {
            List<Activation> activationList = new ArrayList<>();
            for(Activation a: activations) {
                Optional<SmsModel> smsModelOptional = smsRepository.findFirstByChipnumberAndIdActivationOrderByDateDesc(a.getChipNumber(), a.getId());
                if(smsModelOptional.isPresent() && !(smsModelOptional.get().getMsg().equals(a.getSmsStringModels().get(0)))) {
                    if(smsModelOptional.isPresent()) {
                        SmsModel smsModel = smsModelOptional.get();
                        activationService.saveSmsRetry(a, smsModel.getMsg());
                        activationList.add(a);
                    }
                }
            }
            activationRepository.saveAll(activationList);
        }
    }
}
