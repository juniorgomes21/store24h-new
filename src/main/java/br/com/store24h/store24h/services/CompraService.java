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

    public String buyService(String apiKey, Servico servicoDb) {

        //retirar if
        if (true) {
            return "false";
        }

        User user = userDbRepository.findByApiKey(apiKey).get();

        if(user.getCredito().compareTo(servicoDb.getPrice()) >= 0) {
            try {
                //TODO fazer o código de desconto de rédito do usuário.

                return "true";
            } catch (Exception e) {

                return "false";
            }
        } else {
            return "false";
        }
    }
}
