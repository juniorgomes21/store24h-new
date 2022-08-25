package br.com.store24h.store24h.api;

import br.com.store24h.store24h.Funcionalidades.Funcionalidades;
import br.com.store24h.store24h.model.Administrador;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stubs/handler_api")
public class ApiKey {

    @GetMapping("/criarChaveApi")
    public ResponseEntity<String> chaveApi(Authentication authentication) {
        String keyApi = Funcionalidades.gerarKeyApi(authentication);

        return ResponseEntity.ok(keyApi);
    }
}
