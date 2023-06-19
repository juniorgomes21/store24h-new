package br.com.store24h.store24h.ApiKey;

import br.com.store24h.store24h.model.TimeZone;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.nio.charset.StandardCharsets.UTF_8;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
@RestController
@RequestMapping("/stubs/handler_api/")
public class CreateAndUpdateAPIKey {


    @RequestMapping("/createKeyApiMD5")
    public ResponseEntity<String> generateMD5Hashvalue() {
        String userName = "UserSms24h";
        LocalDate dateObj = LocalDate.now(ZoneId.of(TimeZone.BR.getZone()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String date = dateObj.format(formatter);

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
        String secretPhase = "geeks";
        byte[] hashResult = md.digest((date + userName + secretPhase).getBytes(UTF_8));
        String password = bytesToHex(hashResult);

        return ResponseEntity.ok(password);
    }


    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}