package br.com.store24h.store24h.api.remote;

import br.com.store24h.store24h.model.Servico;
import br.com.store24h.store24h.repository.UserDbRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpClient;

@Service
public class Sms24HubAPI {
    @Autowired
    private UserDbRepository userDbRepository;

    /***
     * Número do pedido
     * https://smshub.org/stubs/handler_api.php?api_key=APIKEY&action=getNumber&service=SERVICE&operator=OPERATOR&country=COUNTRY
     * ACCESS_NUMBER:ID:NUMBER - ID é referente a 'Activation'
     * {
     * 	"ACCESS_NUMBER" : "23424:5521983364786"
     * }
     * @param alias_service obrigatório
     * @return o numero como String
     */
    public String getNumber(String alias_service) {
        String apikey = userDbRepository.findByEmail("fernando@fernando.com").get().getApiKey();
        return remoteCall(apikey, alias_service);
    }

    private String remoteCall(String apikey, String alias_service) {
        RestTemplate restTemplate =  new RestTemplate();
        String service_url = "https://smshub.org/stubs/handler_api.php";
        String result = restTemplate.getForObject("{url}?api_key={apikey}&service={alias_service}",
                String.class, service_url, apikey, alias_service);
        JSONObject jsonObject = new JSONObject(result);
        return jsonObject.getString("ACCESS_NUMBER").split(":")[1];
    }

}
