package br.com.store24h.store24h.services.core;

import br.com.store24h.store24h.model.Activation;
import br.com.store24h.store24h.model.Servico;
import br.com.store24h.store24h.model.TimeZone;
import br.com.store24h.store24h.model.User;
import br.com.store24h.store24h.repository.ActivationRepository;
import br.com.store24h.store24h.repository.SmsRepository;
import br.com.store24h.store24h.services.ChipNumberControlService;
import br.com.store24h.store24h.services.CompraService;
import br.com.store24h.store24h.services.SvsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class ActivationService {

    @Autowired
    private ActivationRepository activationRepository;

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

    @Transactional
    public Long newActivation(User user, Servico servico, String chipNumber) {
        try {
//            TODO tirar daqui
            compraService.buyService(user, servico, chipNumber);
            Activation activation = new Activation(servico.getName(), servico.getAlias(), chipNumber);
            activation.setStatusBuz(ActivationStatus.AGUARDANDO_MENSAGENS);
//            smsRepository.deleteByChipnumber(chipNumber);
            return activationRepository.save(activation).getId();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    public void cancelActivation(Long id) {
        Activation activation = activationRepository.findById(id).get();

        controlService.removeService(activation.getChipNumber(), activation.getAliasService());

        activation.setStatus(8);
        activation.setAliasService(activation.getAliasService() + "_cancel");
        activation.setStatusBuz(ActivationStatus.CANCELADA);
        activationRepository.save(activation);

        svsService.addQuantity(activation.getServiceName());
    }

    public void conclude(Long id) {
        Activation activation = activationRepository.findById(id).get();

        activation.setAliasService(activation.getAliasService() + "_finalizada");
        activation.setStatus(6);
        activation.setStatusBuz(ActivationStatus.FINALIZADA);
        activationRepository.save(activation);
    }

    @Transactional
    public boolean awaitNewCode(Long id) {
        Activation activation = activationRepository.findById(id).get();

        if (activation.getStatus() == 8) {
            return false;
        }

        activation.setStatus(3);
        activation.setInitialTime(LocalDateTime.now(ZoneId.of(TimeZone.BR.getZone())));
        activation.setStatusBuz(ActivationStatus.AGUARDANDO_MENSAGENS);
        activationRepository.save(activation);

        return true;
    }
}
