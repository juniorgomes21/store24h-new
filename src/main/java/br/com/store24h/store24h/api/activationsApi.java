package br.com.store24h.store24h.api;

import br.com.store24h.store24h.dto.ActivationsDTO;
import br.com.store24h.store24h.dto.HistoryBuysDTO;
import br.com.store24h.store24h.dto.SmsDTO;
import br.com.store24h.store24h.dto.StatusDTO;
import br.com.store24h.store24h.model.Activation;
import br.com.store24h.store24h.model.TimeZone;
import br.com.store24h.store24h.repository.ActivationRepository;
import br.com.store24h.store24h.services.UserService;
import br.com.store24h.store24h.services.core.ActivationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/stubs/handler_api/activations")
public class activationsApi {

    @Autowired
    private ActivationRepository activationRepository;

    @Autowired
    private ActivationService activationService;

    @Autowired
    private UserService userService;

    @GetMapping("/valids")
    public ResponseEntity<List<Activation>> activationsValids(@RequestParam String api_key) {
        List<Activation> activationList = new ArrayList<>();

        try {
            activationList = activationService.getActivationsValids(api_key);

            return ResponseEntity.ok(activationList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(activationList);
        }
    }

    @PostMapping("/status")
    public ResponseEntity<List<StatusDTO>> statusActivations(@RequestParam String api_key, @RequestBody List<Long> idActivations) {

        List<StatusDTO> statusDTOList = new ArrayList<>();

        if(userService.isValidApiKey(api_key)) {
            List<Activation> activationList = activationRepository.findAllByIdIn(idActivations);

            activationList.forEach( activation -> {
                StatusDTO statusDTO = new StatusDTO(activation);
                statusDTOList.add(statusDTO);
            });
        }

        return ResponseEntity.ok(statusDTOList);
    }

    @GetMapping("/history")
    public ResponseEntity<List<HistoryBuysDTO>> historyActivations(@RequestParam String api_key) {
        List<HistoryBuysDTO> historyBuysDTOS = new ArrayList<>();

        try {
            List<Activation> activationList = activationService.getAllActivationsApiKey(api_key);

            activationList.forEach(a ->{
                HistoryBuysDTO historyBuysDTO = new HistoryBuysDTO(a);
                historyBuysDTOS.add(historyBuysDTO);
            });

            return ResponseEntity.ok(historyBuysDTOS);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(historyBuysDTOS);
        }
    };
}
