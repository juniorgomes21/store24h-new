package br.com.store24h.store24h.services.Adm;

import br.com.store24h.store24h.Funcionalidades.Funcionalidades;
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

    @Autowired
    private Funcionalidades funcionalidades;

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

    public ResponseEntity<Object> getNumber(String service, String operator, String country ) {

        JSONObject myJson = new JSONObject();

        // POSSÍVEIS ERROS

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
}
