package br.com.store24h.store24h.api;

import br.com.store24h.store24h.Funcionalidades.Funcionalidades;
import br.com.store24h.store24h.dto.SmsDTO;
import br.com.store24h.store24h.model.*;
import br.com.store24h.store24h.repository.*;
import br.com.store24h.store24h.services.core.PublicApiService;
import br.com.store24h.store24h.services.UserService;
import com.nimbusds.jose.shaded.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/stubs/handler_api")
public class SmsApi {

    @Autowired
    private AdmDbRepository admDbRepository;

    @Autowired
    private UserDbRepository userDbRepository;

    @Autowired
    private PaisOperadorasDbRepository paisRepository;

    @Autowired
    private ServicosDbRepository servicosRepository;

    @Autowired
    private Funcionalidades funcionalidades;

    @Autowired
    private UserService userService;

    @Autowired
    private SmsRepository smsRepository;

    @Autowired
    private PublicApiService methodsHubService;

    @Autowired
    private ActivationRepository activationRepository;

    @Autowired
    private ChipRepository chipRepository;

    @GetMapping
    public ResponseEntity<Object> entryPoint(@RequestParam("api_key") String apiKey, @RequestParam("action") String action,
                                             Optional<String> country, Optional<String> operator, Optional<String> service,
                                             Optional<Long> id, Optional<Integer> status) throws NoSuchMethodException {

        String responseAPI = "";

        if(!userService.isValidApiKey(apiKey)) { // BAD_KEY
            // "Chave de API inválida"
            responseAPI = "BAD_KEY";

            return ResponseEntity.badRequest().body(responseAPI);
        }

        if(action.equals("getBalancer")){ // GET_BALANCER
            String responseGetBalancer = methodsHubService.getBalancer(apiKey);
            if(responseGetBalancer.equals("BAD_ACTION")) {
                return ResponseEntity.badRequest().body(responseGetBalancer);
            } else {
                return ResponseEntity.ok(responseGetBalancer);
            }


        } else if(action.equals("getNumber")){ // GET_NUMBER

            String responseGetNumber = methodsHubService.getNumber(apiKey, service, operator, country);

            List<String> badResponse = Arrays.asList("BAD_ACTION", "BAD_SERVICE", "ERROR_SQL");

            if(badResponse.contains(responseGetNumber)) {
                return ResponseEntity.badRequest().body(responseGetNumber);
            } else {
                return ResponseEntity.ok(responseGetNumber);
            }


        } else if(action.equals("getStatus")){ // GET_STATUS

            String responseGetStatus = methodsHubService.getStatus(id);

            List<String> badResponse = Arrays.asList("BAD_ACTION", "NO_ACTIVATION", "ERROR_SQL");

            if(badResponse.contains(responseGetStatus)) {
                return ResponseEntity.badRequest().body(responseGetStatus);
            } else {
                return ResponseEntity.ok(responseGetStatus);
            }

        } else if(action.equals("getSms")) {
            SmsDTO smsDTO = methodsHubService.getSms(apiKey, id.get());

            return ResponseEntity.ok(smsDTO);


        } else if (action.equals("setStatus")) {

            String responseSetStatus = methodsHubService.setStatus(status, id);

            List<String> badResponse = Arrays.asList("ERROR_SQL", "BAD_SERVICE", "BAD_ACTION", "NO_ACTIVATION");
            if(badResponse.contains(responseSetStatus)) {
                return ResponseEntity.badRequest().body(responseSetStatus);
            }

            return ResponseEntity.ok(responseSetStatus);
        } else if (action.equals("getPrice")) {
            List<Object> responseGetPrice = methodsHubService.getPrices(service, country);

            return ResponseEntity.ok(responseGetPrice);
        }

        //"Consulta geral malformada"
        responseAPI = "BAD_ACTION";

        return ResponseEntity.badRequest().body(responseAPI);
    }

    @PostMapping("/cancel/activation/{id}")
    public ResponseEntity<Object> cancelActivation(@RequestParam("api_key") String apiKey, @PathVariable Long id) {
        try {
            if(!userService.isValidApiKey(apiKey)) {
                return ResponseEntity.badRequest().build();
            }
            Activation activation = activationRepository.findById(id).get();
            activation.setStatus(8);
            activationRepository.save(activation);

            ChipModel chipModel = chipRepository.findByNumber(activation.getChipNumber());
            chipModel.setAlugado(false);
            chipRepository.save(chipModel);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
