package br.com.store24h.store24h.api;

import br.com.store24h.store24h.Funcionalidades.Funcionalidades;
import br.com.store24h.store24h.Requisicoes.RequisicaoCredito;
import br.com.store24h.store24h.dto.CreditoDTO;
import br.com.store24h.store24h.dto.ErrorResponseDto;
import br.com.store24h.store24h.model.ComprasCredito;
import br.com.store24h.store24h.model.User;
import br.com.store24h.store24h.repository.ComprasCreditoRepository;
import br.com.store24h.store24h.repository.UserDbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/stubs/handler_api")
public class CreditoApi {

    @Autowired
    private UserDbRepository userDbRepository;

    @Autowired
    private ComprasCreditoRepository comprasCreditoRepository;

    /***
     * Efetua a compra de credito para um usuario e registra na taabela de compras.
     * @param requisicaoCredito
     * @return
     */
    @PostMapping("/comprarCredito")
    public ResponseEntity<Object> comprarCredito(@RequestBody @Valid RequisicaoCredito requisicaoCredito) {
        try {
            BigDecimal credito = requisicaoCredito.getValue();

            Funcionalidades.addCredito(userDbRepository, credito);

            ComprasCredito comprasCredito = new ComprasCredito(LocalDateTime.now(), credito);
            comprasCreditoRepository.save(comprasCredito);

            return ResponseEntity.ok().build();
        } catch (Exception e) {

            return ResponseEntity.badRequest().body(new ErrorResponseDto("Ops, algo deu errado!"));
        }
    }

    /***
     * Pega a quantidade disponivel de crédito do usuario.
     * @return
     */
    @GetMapping("/getCredito")
    public ResponseEntity<Object> getCredito() {
        try {
            User user = userDbRepository.findByEmail("fernando@fernando.com").get();


            return ResponseEntity.ok().body(new CreditoDTO(user.getCredito()));
        } catch (Exception e) {

            return ResponseEntity.badRequest().body(new ErrorResponseDto("Ops, algo deu errado!"));
        }
    }

    @GetMapping("/getTableCredito")
    public ResponseEntity<Object> getTableCredito(@PageableDefault(sort = "localDateTime", direction = Sort.Direction.DESC, page = 0, size = 2) Pageable pageable) {
        try {
            Page<ComprasCredito> comprasCreditoPage = comprasCreditoRepository.findAll(pageable);

            return ResponseEntity.ok().body(comprasCreditoPage);
        } catch (Exception e) {

            return ResponseEntity.badRequest().body(new ErrorResponseDto("Ops, algo deu errado!"));
        }
    }
}
