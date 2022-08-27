package br.com.store24h.store24h.Funcionalidades;

import br.com.store24h.store24h.model.Administrador;
import br.com.store24h.store24h.model.User;
import com.nimbusds.jose.shaded.json.JSONObject;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Funcionalidades {
    public static Administrador admLogado(Authentication authentication) {
        Administrador adm = null;
        if(authentication.getPrincipal() instanceof Administrador) {
            adm = (Administrador) authentication.getPrincipal();
        }

        return adm;
    }

    public static String gerarKeyApi(Authentication authentication) {
        String userName = "Fernando";
        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String date = dateObj.format(formatter);

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
        String secretPhase = "geeks"; // exclusively to set for geeks
//        System.out.println("Current Date : " + date);
//        System.out.println("Login Id : " + userName);
//        System.out.println("Secret Phase : " + secretPhase);

        // By using the current date, userName(emailId) and
        // the secretPhase , it is generated
        byte[] hashResult = md.digest((date + userName + secretPhase).getBytes(UTF_8));
        // convert the value to hex
        String password = bytesToHex(hashResult);
//        System.out.println("Generated password.." + password);

        return password;

    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static User userLogado(Authentication authentication) {
        User user = null;
        if(authentication.getPrincipal() instanceof User) {
            user = (User) authentication.getPrincipal();
        }

        return user;
    }

    public static String getNumumeroDisponivel() {
        ArrayList<String> numerosDiponives = new ArrayList<>();
        numerosDiponives.add("+55 66 9 8336-3821");
        numerosDiponives.add("+55 21 9 8323-4367");
        numerosDiponives.add("+55 93 9 9652-5671");
        numerosDiponives.add("+55 21 9 8237-6532");
        numerosDiponives.add("+55 11 9 8523-3231");
        numerosDiponives.add("+55 21 9 9627-5476");
        numerosDiponives.add("+55 91 9 8653-3438");
        numerosDiponives.add("+55 21 9 8336-4786");
        numerosDiponives.add("+55 11 9 9833-8634");
        numerosDiponives.add("+55 21 9 8834-3821");

        int result = (int) ThreadLocalRandom.current().nextLong(0, 9);

        return numerosDiponives.get(result);
    }

    public static JSONObject getNumeroStatus() {
        ArrayList<JSONObject> listJson = new ArrayList();
        JSONObject myJson = new JSONObject();
        int index = (int) ThreadLocalRandom.current().nextLong(0, 3);
        int codigo = (int) ThreadLocalRandom.current().nextLong(1000, 9999);

        myJson.put("STATUS_WAIT_CODE", "Estamos aguardando a chegada de SMS");
        listJson.add(myJson);
        myJson.put("STATUS_WAIT_RETRY: ", "" + codigo);
        listJson.add(myJson);
        myJson.put("STATUS_CANCEL: ", "ativação cancelada ");
        listJson.add(myJson);
        myJson.put("STATUS_OK: ", "" + codigo);
        listJson.add(myJson);

        return listJson.get(index);
    }
}
