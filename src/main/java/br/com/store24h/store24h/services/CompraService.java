package br.com.store24h.store24h.services;

import br.com.store24h.store24h.model.Servico;
import br.com.store24h.store24h.model.User;
import br.com.store24h.store24h.repository.UserDbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompraService {
    @Autowired
    private UserDbRepository userDbRepository;

    public String virifyCredit(String apiKey, Servico servicoDb) {

        User user = userDbRepository.findByApiKey(apiKey).get();

        if(user.getCredito().compareTo(servicoDb.getPrice()) >= 0) {
            return "true";
        } else {
            return "false";
        }
    }
}
