package br.com.store24h.store24h.security;

import br.com.store24h.store24h.model.Administrador;
import br.com.store24h.store24h.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenApp {
    @Value("${forum.jwt.expiration}")
    private String expiration;

    @Value("${forum.jwt.secret}")
    private String secret;

    public String gerarTokenAdm(Authentication authentication) {
        Administrador administrador = (Administrador) authentication.getPrincipal();

        return Jwts.builder()
                .setIssuer("Administrador do store24h")
                .setSubject(administrador.getEmail())
                .signWith(SignatureAlgorithm.HS512, "logado")
                .setExpiration(new Date(System.currentTimeMillis() + 5 * 60 * 100000))
                .compact();
    }


    public String gerarTokenUser(Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        return Jwts.builder()
                .setIssuer("User do store24h")
                .setSubject(user.getEmail())
                .signWith(SignatureAlgorithm.ES512, "logado")
                .setExpiration(new Date(System.currentTimeMillis() + 5 * 60 * 100000))
                .compact();
    }


    public boolean isValidToken(String token) {
        try {
            Jwts.parser().setSigningKey("logado").parseClaimsJws(token);

            return true;
        } catch (Exception e) {

            return false;
        }
    }


    public String getEmail(String token) {
        Claims claims = Jwts.parser().setSigningKey("logado").parseClaimsJws(token).getBody();

        return claims.getSubject();
    }

    public String getCpf(String token) {
        Claims claims = Jwts.parser().setSigningKey("logado").parseClaimsJws(token).getBody();

        return claims.getSubject();
    }
}
