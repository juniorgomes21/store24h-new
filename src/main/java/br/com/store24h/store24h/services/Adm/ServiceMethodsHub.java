package br.com.store24h.store24h.services.Adm;

import br.com.store24h.store24h.model.User;
import br.com.store24h.store24h.repository.UserDbRepository;
import com.nimbusds.jose.shaded.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Service
public class ServiceMethodsHub {

    @Autowired
    private ServicesUser servicesUser;

    @Autowired
    private UserDbRepository userDbRepository;

    public ResponseEntity<Object> getBalance(String api_key) {
        JSONObject myJson = new JSONObject();

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
}
