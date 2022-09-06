package br.com.store24h.store24h.TokenTest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stubs/handler_api")
public class TestToken {

    @GetMapping("/testartoken")
    public ResponseEntity<Boolean> isValidToken() {

        return ResponseEntity.ok(true);
    }

}
