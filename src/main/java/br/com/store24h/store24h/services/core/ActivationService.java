package br.com.store24h.store24h.services.core;

import br.com.store24h.store24h.model.Activation;
import br.com.store24h.store24h.model.Servico;
import br.com.store24h.store24h.model.SmsModel;
import br.com.store24h.store24h.model.User;
import br.com.store24h.store24h.repository.ActivationRepository;
import br.com.store24h.store24h.repository.SmsRepository;
import br.com.store24h.store24h.services.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ActivationService {

    @Autowired
    private ActivationRepository activationRepository;

    @Autowired
    private CompraService compraService;

    @Autowired
    private SmsRepository smsRepository;

    @Transactional
    public Long newActivation(User user, Servico servico, String chipNumber) {
        try {
//            TODO tirar daqui
            compraService.buyService(user, servico, chipNumber);
            Activation activation = new Activation(servico.getName(), chipNumber);

            smsRepository.deleteByChipnumber(chipNumber);
            return activationRepository.save(activation).getId();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

//        Long idAct = activationRepository.save(activation).getId();

    }
}
