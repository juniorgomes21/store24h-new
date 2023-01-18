package br.com.store24h.store24h.services.core;

import br.com.store24h.store24h.Funcionalidades.Funcionalidades;
import br.com.store24h.store24h.model.ChipModel;
import br.com.store24h.store24h.model.Servico;
import br.com.store24h.store24h.model.User;
import br.com.store24h.store24h.repository.ChipRepository;
import br.com.store24h.store24h.repository.UserDbRepository;
import br.com.store24h.store24h.services.CompraService;
import com.nimbusds.jose.shaded.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PublicApiService {

    @Autowired
    private CompraService compraService;

    @Autowired
    private UserDbRepository userDbRepository;

    @Autowired
    private ChipRepository chipRepository;

    @Autowired
    private ServicesHubService servicesHubService;

    @Autowired
    private Funcionalidades funcionalidades;

    @Autowired
    private ActivationService activationService;

    public String getBalancer(String api_key) {
        String responseAPI = "";


        if (false) { // ERROR_SQL
            //"erro SQL-server"
            responseAPI = "ERROR_SQL";
            return responseAPI;
        }

        if(false) { // BAD_ACTION
            //"Consulta geral malformada"
            responseAPI = "BAD_ACTION";
            return responseAPI;
        }

        // RESPOSTA DO SERVIDOR
        User user = userDbRepository.findByApiKey(api_key).get();
        BigDecimal saldoUser = user.getCredito();
        responseAPI = "ACCESS_BALANCE:" + saldoUser;

        return responseAPI;
    }

    public String getNumber(String apiKey, Optional<String> service, Optional<String> operator, Optional<String> country ) {

        String responseAPI = "";
//        https://apcodes.top:9081/stubs/handler_api?api_key=d750b75c9a683217a0ede2a8d2ca4cac&action=getNumber&service=wa&operator=VIVO&country=73
        // POSSÍVEIS ERROS
        if (!service.isPresent() || !operator.isPresent() || !country.isPresent()) { // BAD_ACTION
            //"Consulta geral malformada"
            responseAPI = "BAD_ACTION";

            return responseAPI;
        }

        Optional<Servico> servicoOptional = servicesHubService.getService(service.get());
        if (!servicoOptional.isPresent()) { // BAD_SERVICE
            //"nome de serviço incorreto"
            responseAPI = "BAD_SERVICE";

            return responseAPI;
        }

//        if (false) { // ERROR_SQL
//            myJson.put("ERROR_SQL", "Erro de banco de dados SQL Server");
//            return ResponseEntity.badRequest().body(myJson);
//        }

        Pageable pageable = PageRequest.of(0, 1);
        List<ChipModel> numeroDisponivelList = chipRepository.findByAlugadoAndAtivoAndOperadora(false, true, operator.get(), pageable);

        // RESPOSTAS DO SERVIDOR - BUZ
        if (numeroDisponivelList.isEmpty()) { // NO_NUMBERS
            // "Não há números com os parâmetros especificados, tente novamente mais tarde ou altere a operadora"
            responseAPI = "NO_NUMBERS";

            return responseAPI;
        }

//        if (false) { // WRONG_SERVICE
//            myJson.put("WRONG_SERVICE", "Identificador de serviço inválido");
//            return ResponseEntity.badRequest().body(myJson);
//        }

        String hasCredit = compraService.virifyCredit(apiKey, servicoOptional.get());

        if (hasCredit.equals("false")) { // NO_BALANCE
            //"A chave da API ficou sem dinheiro suficiente"
            responseAPI = "NO_BALANCE";
            return responseAPI;
        }

        Long idActivation = activationService.newActivation(service.get(), numeroDisponivelList.get(0).getNumber());

        if(idActivation == null) {
            return "";
        }

        //TODO fazer um DTO para ChipModel.
        responseAPI = "ACCESS_NUMBER:" + idActivation + ":" + numeroDisponivelList.get(0).getNumber();

        ChipModel chipModel = chipRepository.findByNumber(numeroDisponivelList.get(0).getNumber());
        chipModel.setAlugado(true);
        chipRepository.save(chipModel);

        return responseAPI;
    }


    public ResponseEntity<Object> getStatus(String id) {
        JSONObject myJson = new JSONObject();

        // POSSÍVEIS ERROS
        if(false) {
            myJson.put("BAD_ACTION", "Consulta geral malformada");
            return ResponseEntity.badRequest().body(myJson);
        }
        if(false) {
            myJson.put("NO_ATIVATION", "ID de ativação não existe");
            return ResponseEntity.badRequest().body(myJson);
        }
        if(false) {
            myJson.put("ERROR_SQL", "Erro no banco de dados do servidor SQL, entre em contato com seu administrador");
            return ResponseEntity.badRequest().body(myJson);
        }

        JSONObject stausCode = funcionalidades.getNumeroStatus();

        return ResponseEntity.ok().body(stausCode);
    }

    public ResponseEntity<Object> getNumberStatus(String country, String operator) {
        JSONObject myJson = new JSONObject();

        // POSSÍVEIS ERROS
        if (false) { // ERROR_SQL
            myJson.put("ERROR_SQL", "erro SQL-server");
            return ResponseEntity.badRequest().body(myJson);
        }
        if(false) { // BAD_ACTION
            myJson.put("BAD_ACTION", "Consulta geral malformada");
            return ResponseEntity.badRequest().body(myJson);
        }

        //RESPOSTA DO SERVIDOR
        myJson.put("vk_0", 185);
        myJson.put("ok_0", 131);
        myJson.put("wa_0", 96);
        myJson.put("vi_0", 49);
        myJson.put("tg_0", 118);
        myJson.put("wb_0", 74);
        myJson.put("go_0", 99);
        myJson.put("fb_0", 128);
        myJson.put("tw_0", 244);
        myJson.put("av_0", 99);

        return ResponseEntity.ok().body(myJson);
    }

    public ResponseEntity<?> setStatus(String status, String id) {
        JSONObject myJson = new JSONObject();


        // POSSÍVEIS ERROS
        if(false) { // BAD_ACTION
//            1 - Notify that SMS has been sent (optional)
//            3 - Request another SMS
//            6 - Confirm SMS code and complete activation
//            8 - Cancel activation
            myJson.put("BAD_ACTION", "Consulta geral malformada");
            return ResponseEntity.badRequest().body(myJson);
        }
        if(false) { // BAD_SERVICE
            myJson.put("BAD_SERVICE", "nome de serviço incorreto");
            return ResponseEntity.badRequest().body(myJson);
        }
        if(false) { // NO_ATIVATION
            myJson.put("NO_ATIVATION", "ID de ativação não existe");
            return ResponseEntity.badRequest().body(myJson);
        }
        if(false) { // ERROR_SQL
            myJson.put("ERROR_SQL", "Erro no banco de dados do servidor SQL, entre em contato com seu administrador");
            return ResponseEntity.badRequest().body(myJson);
        }

        // RESPOSTAS DO SERVIDOR
        if (false) { // ACCESS_READY
            myJson.put("ACCESS_READY", "Prontidão de espera de SMS");
            return ResponseEntity.badRequest().body(myJson);
        }
        if (false) { // ACCESS_RETRY_GET
            myJson.put("ACCESS_RETRY_GET", "Esperamos um novo SMS");
            return ResponseEntity.badRequest().body(myJson);
        }
        if (false) { // ACCESS_CANCEL
            myJson.put("ACCESS_CANCEL", "Ativação cancelada");
            return ResponseEntity.badRequest().body(myJson);
        }

        return ResponseEntity.ok().body("new StatusPost()");
    }

    public ResponseEntity<?> getPrices(String service, String country) {
        JSONObject myJson = new JSONObject();

        myJson.put("Price", "15,50");
        myJson.put("Service", service);
        myJson.put("country", country);

        return ResponseEntity.ok().body(myJson);
    }
}
