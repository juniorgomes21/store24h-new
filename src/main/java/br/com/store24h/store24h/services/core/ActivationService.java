package br.com.store24h.store24h.services.core;

import br.com.store24h.store24h.model.Activation;
import br.com.store24h.store24h.repository.ActivationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivationService {

    @Autowired
    private ActivationRepository activationRepository;

    public Long newActivation(String serviceName, String chipNumber) {
        // da um new na Activation

        // obter id Activation
        Activation activation = new Activation(serviceName, chipNumber);

//        Long idAct = activationRepository.save(activation).getId();

        return activationRepository.save(activation).getId();
    }
}
