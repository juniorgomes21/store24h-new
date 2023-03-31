package br.com.store24h.store24h.services.core;

import br.com.store24h.store24h.model.*;
import br.com.store24h.store24h.repository.ActivationRepository;
import br.com.store24h.store24h.repository.BuyServiceRepository;
import br.com.store24h.store24h.repository.SmsRepository;
import br.com.store24h.store24h.repository.UserDbRepository;
import br.com.store24h.store24h.services.ChipNumberControlService;
import br.com.store24h.store24h.services.CompraService;
import br.com.store24h.store24h.services.SvsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;

@Service
public class ActivationService {

    @Autowired
    private ActivationRepository activationRepository;

    @Autowired
    private UserDbRepository userDbRepository;

    @Autowired
    private CompraService compraService;

    @Autowired
    private SmsRepository smsRepository;

    @Autowired
    private ServiceMapAlg serviceMapAlg;

    @Autowired
    private ChipNumberControlService controlService;

    @Autowired
    private SvsService svsService;

    @Autowired
    private BuyServiceRepository buyServiceRepository;

    @Transactional
    public Long newActivation(User user, Servico servico, String chipNumber, String apiKey) {
        try {
            Activation activation = new Activation(servico, chipNumber, apiKey);
            activation.setStatusBuz(ActivationStatus.AGUARDANDO_MENSAGENS);
            Long id = activationRepository.save(activation).getId();

            compraService.subtractAndSave(user, servico, chipNumber, id);

            return id;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    public String cancelActivation(Long id, String apiKey) {
        Activation activation = activationRepository.findById(id).get();

        if(activation.getSmsStringModels().isEmpty()) {
            controlService.removeService(activation.getChipNumber(), activation.getAliasService());

            activation.setStatus(8);
            activation.setAliasService(activation.getAliasService() + "_cancel");
            activation.setStatusBuz(ActivationStatus.CANCELADA);
            activationRepository.save(activation);

            saveStatusBuy(activation.getId(), 8, null);

            svsService.addQuantity(activation.getServiceName());

            compraService.devolution(apiKey, activation.getServicePrice());

            return "ACCESS_CANCEL";
        } else {
            this.conclude(id);

            return "ACCESS_ACTIVATION";
        }
    }

    public void conclude(Long id) {
        Activation activation = activationRepository.findById(id).get();

        activation.setAliasService(activation.getAliasService() + "_finalizada");
        activation.setStatus(6);
        activation.setStatusBuz(ActivationStatus.FINALIZADA);


        activationRepository.save(activation);
    }

    public Activation saveSms(Activation a, String sms) {
        a.setEndTime(LocalDateTime.now(ZoneId.of(TimeZone.BR.getZone())));
        a.getSmsStringModels().add(sms);
        a.setStatus(7);
        a.setStatusBuz(ActivationStatus.RECEBIDA);

        saveStatusBuy(a.getId(), 7, sms);

        return a;
    }

    public Activation saveSmsRetry(Activation a, String sms) {
        a.setEndTime(LocalDateTime.now(ZoneId.of(TimeZone.BR.getZone())));
        a.getSmsStringModels().remove(0);
        a.getSmsStringModels().add(sms);
        a.setStatus(7);
        a.setStatusBuz(ActivationStatus.RECEBIDA);

        saveStatusBuy(a.getId(), 7, sms);

        return a;
    }

    @Transactional
    public boolean awaitNewCode(Long id) {
        Activation activation = activationRepository.findById(id).get();

        if (activation.getStatus() == 8) {
            return false;
        }

        activation.setStatus(3);
//        activation.setInitialTime(LocalDateTime.now(ZoneId.of(TimeZone.BR.getZone())));
        activation.setStatusBuz(ActivationStatus.AGUARDANDO_MENSAGENS);
        activationRepository.save(activation);

        saveStatusBuy(activation.getId(), 3, null);

        return true;
    }

    public List<Activation> getActivationsValids(String apiKey) {
        LocalDateTime date = LocalDateTime.now(ZoneId.of(TimeZone.BR.getZone())).minusMinutes(20);
        List<Integer> statusList = Arrays.asList(8, 6);
        List<Activation> activationList = activationRepository.findByApiKeyAndInitialTimeAfterAndStatusNotIn(apiKey, date, statusList);

        return activationList;
    }

    public List<Activation> getAllActivationsApiKey(String apiKey) {
        List<Activation> activationList = activationRepository.findByApiKey(apiKey);

        return activationList;
    }

    private void saveStatusBuy(Long idActivation, int status, String sms) {
        CompraServiso compraServico = buyServiceRepository.findByIdActivation(idActivation);
        if(sms != null) {
            compraServico.setSms(sms);
        }
        compraServico.setStatus(status);
        buyServiceRepository.save(compraServico);
    }

}
