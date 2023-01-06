package br.com.store24h.store24h.api;

import br.com.store24h.store24h.Requisicoes.RequisitionNewPassword;
import br.com.store24h.store24h.services.Adm.ServicesUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/stubs/handler_api")
public class AlterarSenha {

    @Autowired
    private ServicesUser servicesUser;

    @PostMapping("/edit/password")
    public ResponseEntity<String> editPassword(@RequestBody @Valid RequisitionNewPassword requisitionNewPassword, Authentication authentication) {
        try {

            String currentPassword = requisitionNewPassword.getNewPassword();

            if(!(currentPassword.equals(requisitionNewPassword.getNewPassword2()))) {
                return ResponseEntity.badRequest().body("As senhas não são iguais!");
            }

            if (servicesUser.testPassword(currentPassword, authentication)) {
                return ResponseEntity.badRequest().body("Sua senha está incorreta!");
            }

            servicesUser.updatePassword(authentication, currentPassword);

            return ResponseEntity.ok().body("Senha alterada!");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ops, Algo deu errado!");
        }
    }
}
