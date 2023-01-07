package br.com.store24h.store24h.api;

import br.com.store24h.store24h.Funcionalidades.Funcionalidades;
import br.com.store24h.store24h.dto.UserDTO;
import br.com.store24h.store24h.model.User;
import br.com.store24h.store24h.repository.UserDbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stubs/handler_api")
public class UserDetails {

    @Autowired
    private UserDbRepository userDbRepository;

    @Autowired
    private Funcionalidades funcionalidades;

    @GetMapping("/userDetails")
    public ResponseEntity<UserDTO> hellow(Authentication authentication) {
        User user = funcionalidades.userLogado(authentication);

        UserDTO userDTO = new UserDTO(user);

        return ResponseEntity.ok(userDTO);
    }
}
