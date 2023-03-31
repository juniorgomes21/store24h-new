package br.com.store24h.store24h.services;

import br.com.store24h.store24h.model.Servico;
import br.com.store24h.store24h.model.User;
import br.com.store24h.store24h.repository.BuyServiceRepository;
import br.com.store24h.store24h.repository.UserDbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CompraService {
    @Autowired
    private UserDbRepository userDbRepository;

    @Autowired
    private BuyServiceRepository buyServiceRepository;

    @Autowired
    private SvsService svsService;

    public String virifyCredit(User user, Servico servicoDb) {
        if(user.getCredito().compareTo(servicoDb.getPrice()) >= 0) {
            return "true";
        } else {
            return "false";
        }
    }

    public void subtractAndSave(User user, Servico servico, String chipNumber, Long idActivation) {
        BigDecimal bd = user.getCredito().subtract(servico.getPrice());
        user.setCredito(bd);
        userDbRepository.save(user);

        svsService.saveRegisterBuy(idActivation, servico, chipNumber, user.getId());
    }

    public void devolution(String apiKey, BigDecimal price) {
        User user = userDbRepository.findByApiKey(apiKey).get();
        BigDecimal bd = user.getCredito().add(price);
        user.setCredito(bd);

        userDbRepository.save(user);
    }
}
