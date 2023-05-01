package br.com.store24h.store24h.api.hub;

import br.com.store24h.store24h.Funcionalidades.Funcionalidades;
import br.com.store24h.store24h.dto.ApiKeyDTO;
import br.com.store24h.store24h.dto.ErrorResponseDto;
import br.com.store24h.store24h.model.Administrador;
import br.com.store24h.store24h.model.User;
import br.com.store24h.store24h.repository.UserDbRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/stubs/handler_api")
public class ApiKey {

    @Autowired
    private UserDbRepository userDbRepository;

    @Autowired
    private Funcionalidades funcionalidades;

    @GetMapping("/criarChaveApi")
    public ResponseEntity chaveApi(Authentication authentication) {
        JSONObject myJson = new JSONObject();
        User user = funcionalidades.userLogado(authentication);

        if (user.getApiKey() == null) {
            try {
                String apiKey = funcionalidades.gerarKeyApi("null");
                user.setApiKey(apiKey);
                userDbRepository.save(user);
                myJson.put("apiKey", apiKey);

                return ResponseEntity.ok().body(myJson);
            } catch (Exception e) {

                return ResponseEntity.badRequest().body("Ops aconteceu um erro, apiKey não criada!");
            }
        } else {

            return ResponseEntity.badRequest().body("Você não pode ter mais de uma ApiKey");
        }
    }

    @GetMapping("/getApiKey")
    public ResponseEntity<Object> getApiKey(Authentication authentication) {
        try {
            User user = funcionalidades.userLogado(authentication);

            return ResponseEntity.ok().body(new ApiKeyDTO(user.getApiKey()));
        } catch (Exception e) {

            return ResponseEntity.badRequest().body(new ErrorResponseDto("Ops, algo deu errado!"));
        }
    }
}
