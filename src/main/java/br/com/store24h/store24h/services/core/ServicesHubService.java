package br.com.store24h.store24h.services.core;

import br.com.store24h.store24h.model.Servico;
import br.com.store24h.store24h.repository.ServicosDbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicesHubService {

    @Autowired
    private ServicosDbRepository servicosDbRepository;

    public Optional<Servico> getService(String aliasService) {
        Optional<Servico> servico = servicosDbRepository.findByAlias(aliasService);

        return  servico;
    }
}
