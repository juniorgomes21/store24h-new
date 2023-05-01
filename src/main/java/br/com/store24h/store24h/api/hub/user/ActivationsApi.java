package br.com.store24h.store24h.api.hub.user;

import br.com.store24h.store24h.dto.ActivationsDTO;
import br.com.store24h.store24h.dto.HistoryBuysDTO;
import br.com.store24h.store24h.dto.StatusDTO;
import br.com.store24h.store24h.model.Activation;
import br.com.store24h.store24h.model.TimeZone;
import br.com.store24h.store24h.model.User;
import br.com.store24h.store24h.repository.ActivationRepository;
import br.com.store24h.store24h.services.UserService;
import br.com.store24h.store24h.services.core.ActivationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/stubs/handler_api/activations")
public class ActivationsApi {

    @Autowired
    private ActivationRepository activationRepository;

    @Autowired
    private ActivationService activationService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<ActivationsDTO>> getSmsApi(Authentication authentication) {
        try {
            User user = userService.userLogado(authentication);
            List<ActivationsDTO> activationsDTOList = new ArrayList<>();
            List<Activation> activationList = activationService.getActivationsValids(user.getApiKey());

            for (Activation a: activationList) {
                ActivationsDTO activationsDTO = new ActivationsDTO();
                LocalDateTime dataCriacao = a.getInitialTime().plusMinutes(21);
                LocalDateTime agora = LocalDateTime.now(ZoneId.of(TimeZone.BR.getZone()));
                Duration duracao = Duration.between(dataCriacao, agora);
                long minutosPassados = duracao.getSeconds() / 60;

                activationsDTO.setIdUser(-1L);
                activationsDTO.setIdActivation(a.getId());
                activationsDTO.setNameService(a.getServiceName());
                activationsDTO.setAliasService(a.getAliasService());
                activationsDTO.setNumberActivation(a.getChipNumber());
                activationsDTO.setRetry(a.getStatus() == 3);
                activationsDTO.setMin(String.valueOf(Math.abs(minutosPassados)));

                if(a.getSmsStringModels().isEmpty() || a.getStatus() == 3) {
                    activationsDTO.setStatus("Envie o código para o número recebido");
                    activationsDTO.setAwaitSms(true);
                } else {
                    activationsDTO.setStatus("Confirme a exatidão do código");
                    activationsDTO.getSmsList().add(a.getSmsStringModels().get(0));
                    activationsDTO.setAwaitSms(false);
                    activationsDTO.setFinalized(true);
                }

                activationsDTOList.add(activationsDTO);
            }

            Collections.reverse(activationsDTOList);

            return ResponseEntity.ok(activationsDTOList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

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
