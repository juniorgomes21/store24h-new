package br.com.store24h.store24h.services;

import br.com.store24h.store24h.model.CompraServiso;
import br.com.store24h.store24h.model.Servico;
import br.com.store24h.store24h.model.User;
import br.com.store24h.store24h.repository.CompraServicoRepository;
import br.com.store24h.store24h.repository.UserDbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CompraService {
    @Autowired
    private UserDbRepository userDbRepository;

    @Autowired
    private CompraServicoRepository compraServicoRepository;

    public String virifyCredit(User user, Servico servicoDb) {



        if(user.getCredito().compareTo(servicoDb.getPrice()) >= 0) {
            return "true";
        } else {
            return "false";
        }
    }

    public void buyService(User user, Servico servico, String chipNumber) {
        BigDecimal bd = user.getCredito().subtract(servico.getPrice());
        user.setCredito(bd);
        userDbRepository.save(user);

        CompraServiso compraServiso = new CompraServiso(servico.getName(), chipNumber);
        compraServicoRepository.save(compraServiso);
    }
}
