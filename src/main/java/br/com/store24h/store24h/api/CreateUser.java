package br.com.store24h.store24h.api;

import br.com.store24h.store24h.Requisicoes.RequisicaoNovoUser;
import br.com.store24h.store24h.model.User;
import br.com.store24h.store24h.repository.UserDbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/stubs/handler_api")
public class CreateUser {

    @Autowired
    private UserDbRepository userDbRepository;

    @PostMapping("/createUser")
    public ResponseEntity createUser(@RequestBody @Valid RequisicaoNovoUser requisicaoNovoUser, UriComponentsBuilder uriBuilder) {

        Optional<User> userOptional = userDbRepository.findByEmail(requisicaoNovoUser.getEmail());

        if(requisicaoNovoUser.getSenhaUser().equals(requisicaoNovoUser.getSenhaUser2()) & !userOptional.isPresent()) {
            User user = requisicaoNovoUser.toUser();
            userDbRepository.save(user);

            URI uri = uriBuilder.path("/createUser/{id}").buildAndExpand(user.getId()).toUri();

            return ResponseEntity.created(uri).body(new RequisicaoNovoUser(user));
        }

        return ResponseEntity.badRequest().body("error");
    }
}
