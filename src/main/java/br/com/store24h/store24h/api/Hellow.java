package br.com.store24h.store24h.api;

import br.com.store24h.store24h.dto.NumberStatus;
import com.nimbusds.jose.shaded.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hellow {

    @GetMapping("/")
    public ResponseEntity<String> hellow() {

        return ResponseEntity.ok("hellow");
    }

}
