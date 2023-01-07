package br.com.store24h.store24h.api;

import br.com.store24h.store24h.Funcionalidades.Funcionalidades;
import br.com.store24h.store24h.Requisicoes.RequisicaoNovoUser;
import br.com.store24h.store24h.dto.ErrorCadastroDTO;
import br.com.store24h.store24h.model.User;
import br.com.store24h.store24h.repository.UserDbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/stubs/handler_api")
public class CreateUserX {

    @Autowired
    private UserDbRepository userDbRepository;

    @Autowired
    private Funcionalidades funcionalidades;

    @PostMapping("/createUser")
    public ResponseEntity<Object> createUser(@RequestBody @Valid RequisicaoNovoUser requisicaoNovoUser, UriComponentsBuilder uriBuilder) {

        Optional<User> userOptional = userDbRepository.findByEmail(requisicaoNovoUser.getEmail());

        if(requisicaoNovoUser.getSenhaUser().equals(requisicaoNovoUser.getSenhaUser2()) & !userOptional.isPresent()) {
            User user = requisicaoNovoUser.toUser(funcionalidades);
            userDbRepository.save(user);

            URI uri = uriBuilder.path("/createUser/{id}").buildAndExpand(user.getId()).toUri();

            return ResponseEntity.created(uri).body(new RequisicaoNovoUser(user));
        } else if (userOptional.isPresent()) {

            return ResponseEntity.badRequest().body(new ErrorCadastroDTO("Este Email já ésta cadastrado!"));

        } else if (!requisicaoNovoUser.getSenhaUser().equals(requisicaoNovoUser.getSenhaUser2())) {

            return ResponseEntity.badRequest().body(new ErrorCadastroDTO("As senhas não correspondem!"));
        }

        return ResponseEntity.badRequest().body(new ErrorCadastroDTO("Ops, aconteceu um erro inesperado!"));
    }
}
