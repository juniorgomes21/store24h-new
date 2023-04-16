package br.com.store24h.store24h.api;

import br.com.store24h.store24h.Funcionalidades.Funcionalidades;
import br.com.store24h.store24h.dto.SmsDTO;
import br.com.store24h.store24h.model.*;
import br.com.store24h.store24h.repository.*;
import br.com.store24h.store24h.services.ChipNumberControlService;
import br.com.store24h.store24h.services.SvsService;
import br.com.store24h.store24h.services.core.ActivationService;
import br.com.store24h.store24h.services.core.ActivationStatus;
import br.com.store24h.store24h.services.core.PublicApiService;
import br.com.store24h.store24h.services.UserService;
import com.nimbusds.jose.shaded.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    private ServicosRepository servicosRepository;

    @Autowired
    private ActivationRepository activationRepository;

    @Autowired
    private ChipRepository chipRepository;

    @Autowired
    private SvsService svsService;

    @Autowired
    private ChipNumberControlService controlService;

    @Autowired
    private Funcionalidades funcionalidades;

    @Autowired
    private UserService userService;

    @Autowired
    private PublicApiService methodsHubService;

    @Autowired
    private ActivationService activationService;

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

        if(action.equals("getBalance")){ // GET_BALANCER
            String responseGetBalancer = methodsHubService.getBalancer(apiKey);
            if(responseGetBalancer.equals("BAD_ACTION")) {
                return ResponseEntity.badRequest().body(responseGetBalancer);
            } else {
                return ResponseEntity.ok(responseGetBalancer);
            }


        } else if(action.equals("getNumber")){ // GET_NUMBER

            if(country.isPresent() && !country.get().equals("73")) {
                return ResponseEntity.badRequest().body("BAD_SERVICE");
            }

            String responseGetNumber = methodsHubService.getNumber(apiKey, service, operator, country);

            List<String> badResponse = Arrays.asList("BAD_ACTION", "BAD_SERVICE", "ERROR_SQL");

            if(badResponse.contains(responseGetNumber)) {
                // chamada syncrona
                return ResponseEntity.badRequest().body(responseGetNumber);
            } else {
                return ResponseEntity.ok(responseGetNumber);
            }


        } else if(action.equals("getStatus")){ // GET_STATUS

            String responseGetStatus = methodsHubService.getStatus(id, apiKey);

            List<String> badResponse = Arrays.asList("BAD_ACTION", "NO_ACTIVATION", "ERROR_SQL");

            if(badResponse.contains(responseGetStatus)) {
                return ResponseEntity.badRequest().body(responseGetStatus);
            } else {
                return ResponseEntity.ok(responseGetStatus);
            }

        } else if(action.equals("getSms")) {
            SmsDTO smsDTO = methodsHubService.getSms(id.get());

            return ResponseEntity.ok(smsDTO);

        }else if(action.equals("getSmsRetry")) {
            SmsDTO smsDTO = methodsHubService.getSmsRetry(id.get());

            return ResponseEntity.ok(smsDTO);

        } else if (action.equals("setStatus")) {

            String responseSetStatus = methodsHubService.setStatus(status, id, apiKey);

            List<String> badResponse = Arrays.asList("ERROR_SQL", "BAD_SERVICE", "BAD_ACTION", "NO_ACTIVATION");
            if(badResponse.contains(responseSetStatus)) {
                return ResponseEntity.badRequest().body(responseSetStatus);
            }

            return ResponseEntity.ok(responseSetStatus);
        } else if (action.equals("getPrices")) {

            if(country.isPresent() && !country.get().equals("73")) {
                JSONObject myJson = new JSONObject();
                return ResponseEntity.badRequest().body(myJson);
            }

            Object responseGetPrice = methodsHubService.getPrices(service, country);

            if(responseGetPrice == null) {
                return ResponseEntity.badRequest().build();
            }

            return ResponseEntity.ok(responseGetPrice);

        } else if (action.equals("getNumbersStatus")) {

            JSONObject response = methodsHubService.getNumberStatus(country, operator);

            return ResponseEntity.ok(response);
        }

        //"Consulta geral malformada"
        responseAPI = "BAD_ACTION";

        return ResponseEntity.badRequest().body(responseAPI);
    }

    @PostMapping("/conclude/activation/{id}")
    public ResponseEntity<Object> concludeActivation(@RequestParam("api_key") String apiKey, @PathVariable Long id) {
        try {
            if(!userService.isValidApiKey(apiKey)) {
                return ResponseEntity.badRequest().build();
            }

            activationService.conclude(id);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/cancel/activation/{id}")
    public ResponseEntity<Object> cancelActivation(@RequestParam("api_key") String apiKey, @PathVariable Long id) {
        try {
            if(!userService.isValidApiKey(apiKey)) {
                return ResponseEntity.badRequest().build();
            }

            activationService.cancelActivation(id, apiKey);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
