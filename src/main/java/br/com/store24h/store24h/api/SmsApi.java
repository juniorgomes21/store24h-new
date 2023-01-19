package br.com.store24h.store24h.api;

import br.com.store24h.store24h.Funcionalidades.Funcionalidades;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
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

    @GetMapping
    public ResponseEntity<String> entryPoint(@RequestParam("api_key") String apiKey, @RequestParam("action") String action,
                                             Optional<String> country, Optional<String> operator, Optional<String> service,
                                             Optional<String> id) throws NoSuchMethodException {

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

            if(responseGetNumber.isEmpty()) {
                return ResponseEntity.badRequest().body("ERROR_SQL");
            }
            else if(responseGetNumber.equals("BAD_ACTION") || responseGetNumber.equals("BAD_SERVICE") || responseGetNumber.equals("NO_BALANCE")) {
                return ResponseEntity.badRequest().body(responseGetNumber);
            } else {
                return ResponseEntity.ok(responseGetNumber);
            }


        } else if(action.equals("getStatus")){ // GET_STATUS
            // TODO fazer ainda
            return ResponseEntity.badRequest().body("methodsHubService.getStatus(id.get())");
        }

        //"Consulta geral malformada"
        responseAPI = "BAD_ACTION";

        return ResponseEntity.badRequest().body(responseAPI);
    }

    @GetMapping("/getSms")
    public ResponseEntity<Object> getSms(@RequestParam("api_key") String apiKey, @RequestParam("id") Long idActivation) {
        Activation activation = activationRepository.getById(idActivation);
//        Optional<List<SmsModel>> smsModelList = smsRepository.findByDateAfter(activation.getInitialTime());
        Optional<List<SmsModel>> smsModelList = Optional.of(new ArrayList<>());
        if(activation.getSmsStringModels().size() == 0) {
            Pageable pageable = PageRequest.of(0, 1, Sort.by("date").descending());
            smsModelList = smsRepository.findByChipnumber(activation.getChipNumber(), pageable);
        }

        if(!smsModelList.isPresent()) {
            activation.setStatus(7);
            activation.getSmsStringModels().add(smsModelList.get().get(0).getMsg());

            LocalDateTime now = LocalDateTime.now();
            ZoneId brasiliaZone = ZoneId.of("America/Sao_Paulo");
            ZonedDateTime brasiliaNow = now.atZone(brasiliaZone);
            activation.setEndTime(brasiliaNow.toLocalDateTime());

            activationRepository.save(activation);

            return ResponseEntity.ok( smsModelList.get()
                    //.get(0) //Todo ver se lista ou SmsModel
            ); //SmsModel
        }

        ;


        return ResponseEntity.ok( activation.getSmsStringModels()
                //.get(0) //Todo ver se lista ou SmsModel
        ); //SmsModel
    }

//    @GetMapping
//    public ResponseEntity<Object> entryPoint(@RequestParam("api_key") String api_key, @RequestParam("action") String action,
//                                          @RequestParam("country") String country, @RequestParam("operator") String operator) throws NoSuchMethodException {
//
////        @RequestParam("api_key") String api_key,
//        ResponseEntity<Object> result = null;
//
//        if(action.equals("getBalance")){
//            String methodName = action;
//            Class[] parameterTypes = new Class[]{String.class};
//            Object[] parameters = new Object[]{api_key};
//
//            Method method = null;
//            try {
//                method = SmsApi.this.getClass().getMethod(methodName, parameterTypes);
//            } catch (NoSuchMethodException e) {
//                e.printStackTrace();
//            }
//            try {
//                result = (ResponseEntity<Object>) method.invoke(SmsApi.this, parameters);
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (InvocationTargetException e) {
//                e.printStackTrace();
//            }
//        } else if (action.equals("m1")){
//            String methodName = action;
//            Class[] parameterTypes = new Class[]{String.class};
//            Object[] parameters = new Object[]{api_key};
//
//            Method method = SmsApi.this.getClass().getMethod(methodName, parameterTypes);
//            try {
//                result = (ResponseEntity<Object>) method.invoke(SmsApi.this, parameters);
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (InvocationTargetException e) {
//                e.printStackTrace();
//            }
//        } else if (action.equals("m2")){
//            String methodName = action;
//            Class[] parameterTypes = new Class[]{String.class};
//            Object[] parameters = new Object[]{api_key};
//
//            Method method = SmsApi.this.getClass().getMethod(methodName, parameterTypes);
//            try {
//                result = (ResponseEntity<Object>) method.invoke(SmsApi.this, parameters);
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (InvocationTargetException e) {
//                e.printStackTrace();
//            }
//        } else {
//            System.out.println("hhahhahahhahhahahahhah!");
//        }
//
////        JSONObject myJson = new JSONObject();
////        myJson.put("av_0", 99);
////        return ResponseEntity.ok().body(myJson);
//        return result;
//    }




    /***
     * Todas as solicitações devem ter uma chave de API como parâmetro "api_key" "api_key"
     * https://smshub.org/stubs/handler_api.php?api_key=APIKEY&action=getNumbersStatus&country=COUNTRY&operator=OPERATOR
     * {"vk_0":146,"ok_0":133,"wa_0":118,"vi_0":153,"tg_0":108,"wb_0":186,"go_0":0,"av_0":101,"fb_0":3,"tw_0":43}
     * @param api_key obrigatório
     * @param action
     * @param country
     * @param operator
     * @return
     */

    @GetMapping("/getNumberStatus")
    public ResponseEntity<?> numberStatus(@RequestParam("api_key") String api_key, @RequestParam("action") String action,
                                          @RequestParam("country") String country, @RequestParam("operator") String operator) {
        JSONObject myJson = new JSONObject();

//        SmsApi.class.getMethod("numberStatus", )

        // POSSÍVEIS ERROS
        if(!userService.isValidApiKey(api_key)) { // BAD_KEY
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

        if (!isServiceOn()) {
            myJson.put("DEV_MODE", "Tudo OK, mas a API está em mode desenvolvimento, conecte os outros serviços ...");
            return ResponseEntity.badRequest().body(myJson);
        }

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

    /***
     * Consulta de saldo
     * https://smshub.org/stubs/handler_api.php?api_key=APIKEY&action=getBalance
     * {
     * 	"ACCESS_BALANCE": 270
     * }
     * @param api_key obrigatório
     * @param action
     * @return valor em R$
     */
//    @GetMapping("/getBalance")
    public ResponseEntity<Object> getBalance(@RequestParam("api_key") String api_key, @RequestParam("action") String action) {
        JSONObject myJson = new JSONObject();
        System.out.println(action);
        boolean isValidApiKey = false;

        // POSSÍVEIS ERROS
        if(!userService.isValidApiKey(api_key)) { // BAD_KEY
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
        User user = userDbRepository.findByApiKey(api_key).get();
        BigDecimal saldoUser = user.getCredito();
        myJson.put("ACCESS_BALANCE", saldoUser);

        return ResponseEntity.ok().body(myJson);
    }

    /***
     * Número do pedido
     * https://smshub.org/stubs/handler_api.php?api_key=APIKEY&action=getNumber&service=SERVICE&operator=OPERATOR&country=COUNTRY
     * ACCESS_NUMBER:ID:NUMBER - ID é referente a 'Activation'
     * {
     * 	"ACCESS_NUMBER" : "23424:5521983364786"
     * }
     * @param api_key obrigatório
     * @param action
     * @param service obrigatório
     * @param operator
     * @param country
     * @return
     */
    @GetMapping("/getNumber")
    public ResponseEntity<?> number(@RequestParam("api_key") String api_key, @RequestParam("action") String action,
                                    @RequestParam("service") String service, @RequestParam("operator") String operator,
                                    @RequestParam("country") String country ) {


        JSONObject myJson = new JSONObject();

        if (!isServiceOn()) {
            myJson.put("DEV_MODE", "Tudo OK, mas a API está em mode desenvolvimento, conecte os outros serviços ...");
            return ResponseEntity.badRequest().body(myJson);
        }

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
        String numeroDisponivel = funcionalidades.getNumumeroDisponivel();
        myJson.put("numero gerado", numeroDisponivel);

        return ResponseEntity.ok().body(myJson);
    }


    /***
     * Obter status
     * https://smshub.org/stubs/handler_api.php?api_key=APIKEY&action=getStatus&id=ID
     * @param api_key
     * @param action
     * @param id
     * @return
     */
    @GetMapping("/getStatus")
    public ResponseEntity<?> status(@RequestParam("api_key") String api_key, @RequestParam("action") String action, @RequestParam String id) {
        JSONObject myJson = new JSONObject();

        if (!isServiceOn()) {
            myJson.put("DEV_MODE", "Tudo OK, mas a API está em mode desenvolvimento, conecte os outros serviços ...");
            return ResponseEntity.badRequest().body(myJson);
        }

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

        JSONObject stausCode = funcionalidades.getNumeroStatus();

        return ResponseEntity.ok().body(stausCode);
    }

    /***
     * Alterar estado
     * https://smshub.org/stubs/handler_api.php?api_key=APIKEY&action=setStatus&status=STATUS&id=ID
     * 1 - SMS enviado para o número,
     * 3 - SMS precisa ser repetido,
     * 6 - ativação concluída com sucesso,
     * 8 - cancelar ativação
     * @param api_key
     * @param action
     * @param id
     * @return
     */
    @GetMapping("/setStatus")
    public ResponseEntity<?> status(@RequestParam("api_key") String api_key, @RequestParam("action") String action,
                                    @RequestParam("status") String status, @RequestParam String id) {
        JSONObject myJson = new JSONObject();

        if (!isServiceOn()) {
            myJson.put("DEV_MODE", "Tudo OK, mas a API está em mode desenvolvimento, conecte os outros serviços ...");
            return ResponseEntity.badRequest().body(myJson);
        }

        if (!isServiceOn()) {
            myJson.put("DEV_MODE", "Tudo OK, mas a API está em mode desenvolvimento, conecte os outros serviços ...");
            return ResponseEntity.badRequest().body(myJson);
        }

        // POSSÍVEIS ERROS
        if(!isValidKeyApi(api_key)) { // BAD_KEY
            myJson.put("BAD_KEY", "Chave de API inválida");
            return ResponseEntity.badRequest().body(myJson);
        }

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

    /***
     * Solicitar todos os preços
     * https://smshub.org/stubs/handler_api.php?api_key=APIKEY&action=getPrices&service=SERVICE&country=COUNTRY
     * @param api_key
     * @param action
     * @param service
     * @param country
     * @return
     */
    @GetMapping("/prices")
    public ResponseEntity<?> prices(@RequestParam("api_key") String api_key, @RequestParam("action") String action,
                                    @RequestParam("service") String service, @RequestParam("country") String country) {
        JSONObject myJson = new JSONObject();

        if (!isServiceOn()) {
            myJson.put("DEV_MODE", "Tudo OK, mas a API está em mode desenvolvimento, conecte os outros serviços ...");
            return ResponseEntity.badRequest().body(myJson);
        }

        myJson.put("Price", "15,50");
        myJson.put("Service", service);
        myJson.put("country", country);

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

        List<Servico> servicos = servicosRepository.findAll();

        return ResponseEntity.ok().body(servicos);
    }

    private boolean isValidKeyApi(String api_key) {
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
    private boolean isServiceOn(){
        boolean SERVICE_IS_ON = false;
        return SERVICE_IS_ON;
    }
}
