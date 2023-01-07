package br.com.store24h.store24h.api;

import br.com.store24h.store24h.Funcionalidades.Funcionalidades;
import br.com.store24h.store24h.Requisicoes.RequisicaoCredito;
import br.com.store24h.store24h.Requisicoes.RequisicaoNovaCompra;
import br.com.store24h.store24h.Requisicoes.RequisicaoNovoServico;
import br.com.store24h.store24h.dto.ErrorResponseDto;
//import br.com.store24h.store24h.model.Compra;
import br.com.store24h.store24h.model.Conta;
import br.com.store24h.store24h.model.User;
//import br.com.store24h.store24h.repository.CompraRepository;
import br.com.store24h.store24h.repository.ContaReppository;
import br.com.store24h.store24h.repository.UserDbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/financa")
public class UserCompraContaControl {
    @Autowired
    private UserDbRepository userDbRepository;
    @Autowired
    private ContaReppository contaReppository;
//    @Autowired
//    private CompraRepository compraRepository;

    @Autowired
    private Funcionalidades funcionalidades;

    @PostMapping
    public ResponseEntity<Object> adicionarCredito(Authentication authentication, RequisicaoCredito requisicaoCredito){
        try {
            User user = funcionalidades.userLogado(authentication);
//            Conta conta = user.getConta();
//            conta.add(requisicaoCredito.getValue());
            userDbRepository.save(user);
            return ResponseEntity.ok("lol");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    new ErrorResponseDto(
                            UserCompraContaControl.class.getCanonicalName() + " adicionarCredito()"
                    ));
        }
    }


}
