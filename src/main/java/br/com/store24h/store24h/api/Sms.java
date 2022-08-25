package br.com.store24h.store24h.api;

import br.com.store24h.store24h.dto.Balance;
import br.com.store24h.store24h.dto.NumberStatus;
import br.com.store24h.store24h.model.Administrador;
import br.com.store24h.store24h.model.PaisOperadoras;
import br.com.store24h.store24h.model.Servicos;
import br.com.store24h.store24h.model.User;
import br.com.store24h.store24h.repository.AdmDbRepository;
import br.com.store24h.store24h.repository.PaisOperadorasDbRepository;
import br.com.store24h.store24h.repository.ServicosDbRepository;
import br.com.store24h.store24h.repository.UserDbRepository;
import com.nimbusds.jose.shaded.json.JSONObject;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/stubs/handler_api")
public class Sms {

    @Autowired
    private AdmDbRepository admDbRepository;

    @Autowired
    private UserDbRepository userDbRepository;

    @Autowired
    private PaisOperadorasDbRepository paisRepository;

    @Autowired
    private ServicosDbRepository servicosRepository;

    @GetMapping("/getNumberStatus")
    public ResponseEntity<?> numberStatus(@RequestParam("api_key") String api_key, @RequestParam("action") String action, @RequestParam("country") String country, @RequestParam("operator") String operator) {
        JSONObject myJson = new JSONObject();

        // POSSÍVEIS ERROS
        if(verificaKeyApi(api_key)) { // BAD_KEY
            myJson.put("BAD_KEY", "Chave de API inválida");
            return ResponseEntity.badRequest().body(myJson);
        }
        if (false) { // ERROR_SQL
            myJson.put("ERROR_SQL", "erro SQL-server");
            return ResponseEntity.badRequest().body(myJson);
        }
        if(false) { // BAD_ACTION
            myJson.put("BAD_ACTION", "Consulta geral malformada");
            return ResponseEntity.badRequest().body(myJson);
        }

        return ResponseEntity.ok().body(myJson);
    }

    @GetMapping("/getNumberStatux")
    public ResponseEntity<?> numberStatux() {
        JSONObject myJson = new JSONObject();
        // 55 xx 55555-4444
        String[] fakeNumbers = new String[]{55+getDDD()+9+getFour()+getFour()};
        // POSSÍVEIS ERROS
//        if(verificaKeyApi(api_key)) { // BAD_KEY
//            myJson.put("BAD_KEY", "Chave de API inválida");
//            return ResponseEntity.badRequest().body(myJson);
//        }
        if (false) { // ERROR_SQL
            myJson.put("ERROR_SQL", "erro SQL-server");
            return ResponseEntity.badRequest().body(myJson);
        }
        if(false) { // BAD_ACTION
            myJson.put("BAD_ACTION", "Consulta geral malformada");
            return ResponseEntity.badRequest().body(myJson);
        }

        return ResponseEntity.ok().body(random());
    }
    private String random (){
        String result="";
        //55+getDDD()+9+getFour()+getFour()
//        result = "+"+55+"("+11+") "+9+" "+getFour()+"-"+getFour();
        String[] results = new String[]{

        };
        result = results[(int) ThreadLocalRandom.current().nextLong(0, results.length-1)];
        //ArrayList arrayList = new ArrayList<>();

        return result;
    }
    private String getDDD(){
        String result="";
        long l = ThreadLocalRandom.current().nextLong(11, 99);
        return result+l;
    };
    private String getFour(){
        return getDDD()+getDDD();
    }

    @GetMapping("/getBalance")
    public ResponseEntity<?> balance(@RequestParam("api_key") String api_key, @RequestParam("action") String action) {
        JSONObject myJson = new JSONObject();

        // POSSÍVEIS ERROS
        if(verificaKeyApi(api_key)) { // BAD_KEY
            myJson.put("BAD_KEY", "Chave de API inválida");
            return ResponseEntity.badRequest().body(myJson);
        }
        if (false) { // ERROR_SQL
            myJson.put("ERROR_SQL", "erro SQL-server");
            return ResponseEntity.badRequest().body(myJson);
        }
        if(false) { // BAD_ACTION
            myJson.put("BAD_ACTION", "Consulta geral malformada");
            return ResponseEntity.badRequest().body(myJson);
        }

        return ResponseEntity.ok().body(new Balance());
    }

    @GetMapping("/getNumber")
    public ResponseEntity<?> number(@RequestParam("api_key") String api_key, @RequestParam("action") String action, @RequestParam("service") String service, @RequestParam("operator") String operator, @RequestParam("country") String country ) {

        JSONObject myJson = new JSONObject();
        myJson.put("api_key", api_key);
        myJson.put("action", action);
        myJson.put("service", service);
        myJson.put("operator", operator);
        myJson.put("country", country);



        // RESPOSTAS DO SERVIDOR
        if (false) { // NO_NUMBERS
            myJson.put("NO_NUMBERS", "Não há números com os parâmetros especificados, tente novamente mais tarde ou altere a operadora, o país.");
            return ResponseEntity.badRequest().body(myJson);
        }
        if (false) { // NO_BALANCE
            myJson.put("NO_BALANCE", "A chave da API ficou sem dinheiro");
            return ResponseEntity.badRequest().body(myJson);
        }
        if (false) { // WRONG_SERVICE
            myJson.put("WRONG_SERVICE", "Identificador de serviço inválido");
            return ResponseEntity.badRequest().body(myJson);
        }

        // POSSÍVEIS ERROS
        if (false) { // BAD_ACTION
            myJson.put("BAD_ACTION", "Consulta geral malformada");
            return ResponseEntity.badRequest().body(myJson);
        }
        if (false) { // SERVIÇO_RUIM
            myJson.put("BAD_SERVICE", "nome de serviço incorreto");
            return ResponseEntity.badRequest().body(myJson);
        }
        if (verificaKeyApi(api_key)) { // BAD_KEY
            myJson.put("BAD_KEY", "Chave de API inválida");
            return ResponseEntity.badRequest().body(myJson);
        }
        if (false) { // ERROR_SQL
            myJson.put("ERROR_SQL", "Erro de banco de dados SQL Server");
            return ResponseEntity.badRequest().body(myJson);
        }

        return ResponseEntity.ok().body(myJson);
    }


    @GetMapping("/status")
    public ResponseEntity<?> status(@RequestParam("api_key") String api_key, @RequestParam("action") String action, @RequestParam String id) {
        JSONObject myJson = new JSONObject();

        // POSSÍVEIS ERROS
        if(verificaKeyApi(api_key)) { // BAD_KEY
            myJson.put("BAD_KEY", "Chave de API inválida");
            return ResponseEntity.badRequest().body(myJson);
        }
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

        return ResponseEntity.ok("new Status()");
    }

    @PostMapping("/status")
    public ResponseEntity<?> status(@RequestParam("api_key") String api_key, @RequestParam("action") String action, @RequestParam("status") String status, @RequestParam String id) {
        JSONObject myJson = new JSONObject();

        // POSSÍVEIS ERROS
        if(verificaKeyApi(api_key)) { // BAD_KEY
            myJson.put("BAD_KEY", "Chave de API inválida");
            return ResponseEntity.badRequest().body(myJson);
        }
        if(false) { // BAD_ACTION
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

    @GetMapping("/prices")
    public ResponseEntity<?> prices(@RequestParam("api_key") String api_key, @RequestParam("action") String action, @RequestParam("service") String service, @RequestParam("country") String country) {
        JSONObject myJson = new JSONObject();
        myJson.put("Country", "{Service: {Price: Quantity}}");

        return ResponseEntity.ok().body(myJson);
    }

    @GetMapping("/listaDePaisesOperadoras/{nomePais}")
    public ResponseEntity<PaisOperadoras> listaPaises(@PathVariable String nomePais) {
        PaisOperadoras paisOperadoras = paisRepository.findByPais(nomePais).get();

        return ResponseEntity.ok().body(paisOperadoras);
    }
    @GetMapping("/listServicos")
    public ResponseEntity<?> teste() {

        List<Servicos> servicos = servicosRepository.findAll();

        return ResponseEntity.ok().body(servicos);
    }

    public boolean verificaKeyApi(String api_key) {
        try {
//            Optional<User> adm = userDbRepository.findByKeyAPi(api_key);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
