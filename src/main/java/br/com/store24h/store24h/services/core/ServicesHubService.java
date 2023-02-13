package br.com.store24h.store24h.services.core;

import br.com.store24h.store24h.model.Servico;
import br.com.store24h.store24h.repository.ServicosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicesHubService {

    @Autowired
    private ServicosRepository servicosRepository;

    public Optional<Servico> getService(String aliasService) {
        Optional<Servico> servico = servicosRepository.findByAlias(aliasService);

        return  servico;
    }
}
