package br.com.store24h.store24h.api;

import br.com.store24h.store24h.Funcionalidades.Funcionalidades;
import br.com.store24h.store24h.dto.Balance;
import br.com.store24h.store24h.model.PaisOperadoras;
import br.com.store24h.store24h.model.Servicos;
import br.com.store24h.store24h.model.User;
import br.com.store24h.store24h.repository.AdmDbRepository;
import br.com.store24h.store24h.repository.PaisOperadorasDbRepository;
import br.com.store24h.store24h.repository.ServicosDbRepository;
import br.com.store24h.store24h.repository.UserDbRepository;
import com.nimbusds.jose.shaded.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
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
        System.out.println(api_key);
        // POSSÍVEIS ERROS
        if(!isValidKeyApi(api_key)) { // BAD_KEY
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

        // Pergutnar mais tarde !!! importante!!


        // POSSÍVEIS ERROS
        if(!isValidKeyApi(api_key)) { // BAD_KEY
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

        // RESPOSTA DO SERVIDOR
        Optional<User> user = userDbRepository.findByApiKey(api_key);
        int saldoUser = user.get().getSaldo();
        myJson.put("ACCESS_BALANCE", saldoUser);

        return ResponseEntity.ok().body(myJson);
    }

    @GetMapping("/getNumber")
    public ResponseEntity<?> number(@RequestParam("api_key") String api_key, @RequestParam("action") String action, @RequestParam("service") String service, @RequestParam("operator") String operator, @RequestParam("country") String country ) {

        JSONObject myJson = new JSONObject();

        // POSSÍVEIS ERROS
        if (!isValidKeyApi(api_key)) { // BAD_KEY
            myJson.put("BAD_KEY", "Chave de API inválida");
            return ResponseEntity.badRequest().body(myJson);
        }
        if (false) { // BAD_ACTION
            myJson.put("BAD_ACTION", "Consulta geral malformada");
            return ResponseEntity.badRequest().body(myJson);
        }
        if (false) { // SERVIÇO_RUIM
            myJson.put("BAD_SERVICE", "nome de serviço incorreto");
            return ResponseEntity.badRequest().body(myJson);
        }
        if (false) { // ERROR_SQL
            myJson.put("ERROR_SQL", "Erro de banco de dados SQL Server");
            return ResponseEntity.badRequest().body(myJson);
        }

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
        String numeroDisponivel = Funcionalidades.getNumumeroDisponivel();
        myJson.put("numero gerado", numeroDisponivel);

        return ResponseEntity.ok().body(myJson);
    }


    @GetMapping("/status")
    public ResponseEntity<?> status(@RequestParam("api_key") String api_key, @RequestParam("action") String action, @RequestParam String id) {
        JSONObject myJson = new JSONObject();

        // POSSÍVEIS ERROS
        if(!isValidKeyApi(api_key)) { // BAD_KEY
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

        JSONObject stausCode = Funcionalidades.getNumeroStatus();

        return ResponseEntity.ok().body(stausCode);
    }

    @PostMapping("/status")
    public ResponseEntity<?> status(@RequestParam("api_key") String api_key, @RequestParam("action") String action, @RequestParam("status") String status, @RequestParam String id) {
        JSONObject myJson = new JSONObject();

        // POSSÍVEIS ERROS
        if(!isValidKeyApi(api_key)) { // BAD_KEY
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
        myJson.put("Price", "15,50");
        myJson.put("Service", "wa");
        myJson.put("country", "Brasil");


        return ResponseEntity.ok().body(myJson);
    }

    @GetMapping("/listaDePaisesOperadoras/{nomePais}")
    public ResponseEntity<?> listPaises(@PathVariable String nomePais) {

        Optional<List<PaisOperadoras>> paisOperadoras = paisRepository.findByPais(nomePais);
        if(paisOperadoras.isPresent()) {

            return ResponseEntity.ok().body(paisOperadoras.get());
        }

        return ResponseEntity.badRequest().body("Pais não encontrado!");
    }

    @GetMapping("/listServicos")
    public ResponseEntity<?> servicos() {

        List<Servicos> servicos = servicosRepository.findAll();

        return ResponseEntity.ok().body(servicos);
    }

    public boolean isValidKeyApi(String api_key) {
        try {
            Optional<User> user = userDbRepository.findByApiKey(api_key);

            if(user.isPresent()) {
                return true;
            }

            return false;

        } catch (Exception e) {
            return false;
        }
    }
}
