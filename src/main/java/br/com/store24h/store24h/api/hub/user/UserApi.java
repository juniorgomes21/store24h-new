package br.com.store24h.store24h.api.hub.user;

import br.com.store24h.store24h.Funcionalidades.Funcionalidades;
import br.com.store24h.store24h.Requisicoes.RequisitionNewPassword;
import br.com.store24h.store24h.dto.UserDTO;
import br.com.store24h.store24h.model.User;
import br.com.store24h.store24h.repository.UserDbRepository;
import br.com.store24h.store24h.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/stubs/handler_api")
public class UserApi {

    @Autowired
    private UserDbRepository userDbRepository;

    @Autowired
    private Funcionalidades funcionalidades;

    @Autowired
    private UserService userService;

    @GetMapping("/userDetails")
    public ResponseEntity<UserDTO> userDatails(Authentication authentication) {
        User user = funcionalidades.userLogado(authentication);

        UserDTO userDTO = new UserDTO(user);

        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/edit/password")
    public ResponseEntity<String> editPassword(@RequestBody @Valid RequisitionNewPassword requisitionNewPassword, Authentication authentication) {
        try {

            String currentPassword = requisitionNewPassword.getNewPassword();

            if(!(currentPassword.equals(requisitionNewPassword.getNewPassword2()))) {
                return ResponseEntity.badRequest().body("As senhas não são iguais!");
            }

            if (userService.testPassword(currentPassword, authentication)) {
                return ResponseEntity.badRequest().body("Sua senha está incorreta!");
            }

            userService.updatePassword(authentication, currentPassword);

            return ResponseEntity.ok().body("Senha alterada!");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ops, Algo deu errado!");
        }
    }
}
