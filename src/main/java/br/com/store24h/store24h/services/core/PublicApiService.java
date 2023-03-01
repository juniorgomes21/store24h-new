package br.com.store24h.store24h.services.core;

import br.com.store24h.store24h.Funcionalidades.Funcionalidades;
import br.com.store24h.store24h.dto.SmsDTO;
import br.com.store24h.store24h.model.*;
import br.com.store24h.store24h.repository.*;
import br.com.store24h.store24h.services.ChipNumberControlService;
import br.com.store24h.store24h.services.CompraService;
import br.com.store24h.store24h.services.SvsService;
import com.nimbusds.jose.shaded.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PublicApiService {

    @Autowired
    private CompraService compraService;

    @Autowired
    private ActivationService activationService;

    @Autowired
    private ServicesHubService servicesHubService;

    @Autowired
    private Funcionalidades funcionalidades;

    @Autowired
    private ChipNumberControlService controlService;

    @Autowired
    private SvsService svsService;

    @Autowired
    private UserDbRepository userDbRepository;

    @Autowired
    private ChipRepository chipRepository;

    @Autowired
    private ChipNumberControlRepository chipNumberControlRepository;

    @Autowired
    private ActivationRepository activationRepository;

    @Autowired
    private SmsRepository smsRepository;

    @Autowired
    private ServicosRepository servicosRepository;

    @Autowired
    private EntityManager em;

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

    public String getNumber(String apiKey, Optional<String> service, Optional<String> operator, Optional<String> country) {

        String responseAPI = "";

        // POSSÍVEIS ERROS
        if (!service.isPresent() || !country.isPresent()) { // BAD_ACTION
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

        List<ChipModel> numeroDisponivelList = null;

        try {
            if(operator.isPresent()) {
                numeroDisponivelList = chipRepository.findByAlugadoAndAtivoAndOperadora(false, true, operator.get());
            } else {
                numeroDisponivelList = chipRepository.findByAlugadoAndAtivo(false, true);
            }
        } catch (Exception e) {
            return "ERROR_SQL";
        }

        ChipModel chipModel = null;

        // RESPOSTAS DO SERVIDOR - BUZ
        if (numeroDisponivelList.isEmpty()) { // NO_NUMBERS
            // "Não há números com os parâmetros especificados, tente novamente mais tarde ou altere a operadora"
            responseAPI = "NO_NUMBERS";

            return responseAPI;
        } else {
            for(ChipModel cm: numeroDisponivelList) {
//                Optional<Activation> activationOptional = activationRepository.findByChipNumberAndAliasService(cm.getNumber(), service.get());
                Optional<ChipNumberControl> chipNumberControlOptional = chipNumberControlRepository.findByChipNumberAndAliasService(cm.getNumber(), service.get());
                if(!chipNumberControlOptional.isPresent()) {
                    controlService.addServiceInNumber(cm.getNumber(), servicoOptional.get());
                    svsService.subtractQuantity(servicoOptional.get());
                    chipModel = cm;
                    break;
                }
            }

            if (chipModel == null) {
                responseAPI = "NO_NUMBERS";
                return responseAPI;
            }
        }

        User user = userDbRepository.findByApiKey(apiKey).get();

        String hasCredit = compraService.virifyCredit(user, servicoOptional.get());

        if (hasCredit.equals("false")) { // NO_BALANCE
            //"A chave da API ficou sem dinheiro suficiente"
            responseAPI = "NO_BALANCE";
            return responseAPI;
        }

        Long idActivation;
        try {
            idActivation = activationService.newActivation(user, servicoOptional.get(), chipModel.getNumber());
        } catch (Exception e) {
            return "ERROR_SQL";
        }

        responseAPI = "ACCESS_NUMBER:" + idActivation + ":" + chipModel.getNumber();



        return responseAPI;
    }

    public SmsDTO getSms(String apiKey, Long idActivation) {
        Activation activation = activationRepository.getById(idActivation);
//        Optional<List<SmsModel>> smsModel = smsRepository.findByDateAfter(activation.getInitialTime());
        Optional<SmsModel> smsModel = Optional.of(new SmsModel());
        if(activation.getSmsStringModels().size() == 0) {
            String num = activation.getChipNumber();
            smsModel = smsRepository.findByChipnumberAndIdActivation(activation.getChipNumber(), idActivation);
        }

        SmsDTO smsDTO = new SmsDTO();

        if(smsModel.isPresent() && !smsModel.get().getMsg().isEmpty()) {
            activation.setStatus(7);
            String ss = smsModel.get().getMsg();
            activation.getSmsStringModels().add(ss);

            //TODO criar uma função para timeZone
            LocalDateTime now = LocalDateTime.now();
            ZoneId brasiliaZone = ZoneId.of("America/Sao_Paulo");
            ZonedDateTime brasiliaNow = now.atZone(brasiliaZone);
            activation.setEndTime(brasiliaNow.toLocalDateTime());

            activationRepository.save(activation);

//            ChipModel chip = chipRepository.findByNumber(activation.getChipNumber());
//            chip.setAlugado(false);
//            chipRepository.save(chip);

            smsDTO.getSmsList().add(ss);
            smsDTO.setNameService(activation.getServiceName());
            smsDTO.setAliasService(activation.getAliasService());
            smsDTO.setNumberActivation(activation.getChipNumber());

            //Todo ver se lista ou SmsModel
            return smsDTO;
        }
        smsDTO.setNameService(activation.getServiceName());
        smsDTO.setAliasService(activation.getAliasService());
        smsDTO.setSmsList(activation.getSmsStringModels());
        smsDTO.setNumberActivation(activation.getChipNumber());

        return smsDTO;
    }

    public String getStatus(Optional<Long> id) {
        JSONObject myJson = new JSONObject();

        // POSSÍVEIS ERROS
        if(!id.isPresent() && !(id.get() instanceof Long)) {
            //Consulta geral malformada
            return "BAD_ACTION";
        }

        Optional<Activation> activationOptional;

        try {
            //Erro no banco de dados do servidor SQL, entre em contato com seu administrador
            activationOptional = activationRepository.findById(id.get());
        } catch (Exception e) {
            return "ERROR_SQL";
        }

        if(!activationOptional.isPresent()) {
            // ID de ativação não existe
            return "NO_ATIVATION";
        }

        Activation activation = activationOptional.get();

        int statusCode = activation.getStatus();

        if(statusCode == -1) {
            return "STATUS_WAIT_CODE";
        }
        if(false) {
            return "STATUS_WAIT_RETRY:LASTCODE";
        }
        if(statusCode == 8) {
            return "STATUS_CANCEL";
        }
        else {
            return "STATUS_OK:" + activation.getId();
        }
    }

    public String setStatus(Optional<Integer> status, Optional<Long> idActivation) {

        // POSSÍVEIS ERROS
        List<Integer> acceptedStatus = Arrays.asList(1, 3, 6, 8);
        if(!acceptedStatus.contains(status) || !idActivation.isPresent()) { // BAD_ACTION
//            1 - Notificar que o SMS foi enviado (optional)
//            ACCESS_READY -- prontidão de espera SMS

//            3 - Solicitar outro SMS
//            ACCESS_RETRY_GET -- Esperamos um novo SMS

//            6 - Confirme o código SMS e conclua a ativação
//            ACCESS_ACTIVATION -- Ativação concluída com sucesso

//            8 - Cancelar ativação
//            ACCESS_CANCEL -- Ativação cancelada
            return "BAD_ACTION";
        }

        int statusCode = status.get();

        if(false) { // BAD_SERVICE
            //nome de serviço incorreto

            return "BAD_SERVICE";
        }

        Optional<Activation> activationOptional;
        try {
             activationOptional = activationRepository.findById(idActivation.get());
             if(!activationOptional.isPresent()) {
                 return "NO_ATIVATION";
             }
        } catch (Exception e) {
            return "ERROR_SQL";
        }

        // RESPOSTAS DO SERVIDOR
        Activation activation = activationOptional.get();
        if (statusCode == 1) { // ACCESS_READY
            // Prontidão de espera de SMS
            try {
                activation.setStatus(1);
                activationRepository.save(activation);

                return "ACCESS_READY";
            } catch (Exception e) {
                return "ERROR_SQL";
            }

        } else if (statusCode == 3) { // ACCESS_RETRY_GET
            // Esperamos um novo SMS
            try {
                activation.setStatus(3);
                activationRepository.save(activation);

                return "ACCESS_RETRY_GET";
            } catch (Exception e) {
                return "ERROR_SQL";
            }

        } else if (statusCode == 8) { // ACCESS_CANCEL
            // Ativação cancelada
            try {

                activationService.cancelActivation(activation.getId());

                return "ACCESS_CANCEL";
            } catch (Exception e) {
                return "ERROR_SQL";
            }

        } else { // ACCESS_ACTIVATION
            try {
                activation.setStatus(6);
                activationRepository.save(activation);

                return "ACCESS_ACTIVATION";
            } catch (Exception e) {
                return "ERROR_SQL";
            }
        }
    }

    public JSONObject getNumberStatus(Optional<String> country, Optional<String> operator) {
        JSONObject myJson = new JSONObject();

        List<Servico> servicoList = new ArrayList<>();
        try {
            servicoList = servicosRepository.findAll();

            if(operator.isPresent()) {
                List<ChipModel> chipModelList = chipRepository.findByOperadora(operator.get());
                List<String> numbers = new ArrayList<>();
                chipModelList.forEach( chipModel -> {
                    numbers.add(chipModel.getNumber());
                });

                List<ChipNumberControl> chipNumberControlList = chipNumberControlRepository.findByChipNumberIn(numbers);

                servicoList.forEach( servico -> {
                    servico.setTotalQuantity(0);
                    chipNumberControlList.forEach( chipNumberControl -> {
                        List<String> aliasService = chipNumberControl.getAliasService();
                        int index = aliasService.indexOf(servico.getAlias());
                        if(!(index > -1)) {
                            servico.setTotalQuantity(servico.getTotalQuantity() + 1);
                        }
                    });
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            myJson.put("ERROR_SQL", "erro SQL-server");
            return myJson;
        }

        servicoList.forEach( servico -> {
            myJson.put(servico.getAlias() + "_0", servico.getTotalQuantity());
        });

        return myJson;
    }

    public Object getPrices(Optional<String> service, Optional<String> country) {
        if(service.isPresent()) {
            JSONObject myJson = new JSONObject();
            Optional<Servico> servicoOptional = servicosRepository.findByAlias(service.get());
            JSONObject serviceMyJson = new JSONObject();
            if(servicoOptional.isPresent()) {
                Servico s = servicoOptional.get();
                JSONObject priceMyJson = new JSONObject();
                priceMyJson.put("cost", s.getPrice());
                priceMyJson.put("count", s.getTotalQuantity());
                serviceMyJson.put(s.getAlias(), priceMyJson);
                myJson.put("73", serviceMyJson);
            }

            return myJson;
        } else {
            List<Servico> servicoList = servicosRepository.findAll();
            JSONObject myJson = new JSONObject();
            JSONObject serviceMyJson = new JSONObject();
            for(Servico s: servicoList) {
                JSONObject priceMyJson = new JSONObject();
                priceMyJson.put("cost", s.getPrice());
                priceMyJson.put("count", s.getTotalQuantity());
                serviceMyJson.put(s.getAlias(), priceMyJson);
            }
            myJson.put("73", serviceMyJson);

            return myJson;
        }
    }
}
