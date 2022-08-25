package br.com.store24h.store24h.Funcionalidades;

import br.com.store24h.store24h.model.Administrador;
import com.nimbusds.jose.shaded.json.JSONObject;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;

import java.util.Date;

public class Funcionalidades {
    public static Administrador admLogado(Authentication authentication) {
        Administrador adm = null;
        if(authentication.getPrincipal() instanceof Administrador) {
            adm = (Administrador) authentication.getPrincipal();
        }

        return adm;
    }

    public static String gerarKeyApi(Authentication authentication) {
        Administrador adm = Funcionalidades.admLogado(authentication);

        return Jwts.builder()
                .setIssuer("KeyApi")
                .setSubject(adm.getCountry())
                .setAudience(String.valueOf(adm.getSaldo()))
                .signWith(SignatureAlgorithm.HS512, "ApiKeyStore24h")
                .setExpiration(new Date(System.currentTimeMillis() + 5 * 60 * 100000)) // 10000 -- 30 min
                .compact();

    }
}
