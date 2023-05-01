package br.com.store24h.store24h.api.hub.adm;

import br.com.store24h.store24h.dto.ErrorResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stubs/handler_api/adm/activations")
public class ActivationsAdmApi {

    @GetMapping
    public ResponseEntity<Object> activations() {
        try {

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDto());
        }
    }
}
