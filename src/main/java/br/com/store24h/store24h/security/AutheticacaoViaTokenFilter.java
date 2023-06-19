package br.com.store24h.store24h.security;

import br.com.store24h.store24h.model.Administrador;
import br.com.store24h.store24h.model.User;
import br.com.store24h.store24h.repository.AdmDbRepository;
import br.com.store24h.store24h.repository.UserDbRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AutheticacaoViaTokenFilter extends OncePerRequestFilter {

    private TokenApp tokenApp;

    private UserDbRepository userDbRepository;

    private AdmDbRepository admDbRepository;

    public AutheticacaoViaTokenFilter(TokenApp tokenApp, UserDbRepository userDbRepository, AdmDbRepository admDbRepository) {
        this.admDbRepository = admDbRepository;
        this.userDbRepository = userDbRepository;
        this.tokenApp = tokenApp;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = recuperarToken(request);
        if(token != null) {
            boolean valido = tokenApp.isValidToken(token);

            if(valido) {
                String usarioEmail = tokenApp.getEmail(token);
                Optional<User> userOptional = userDbRepository.findByEmail(usarioEmail);

                if (userOptional.isPresent()) {
                    autenticarUser(token);
                } else {
                    autenticarAdm(token);
                }
            }
        }

        filterChain.doFilter(request, response);
    }


    private void autenticarUser(String token) {
        String emailUser = tokenApp.getEmail(token);
        User user = this.userDbRepository.findByEmail(emailUser).get();

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }


    private void autenticarAdm(String token) {
        String idAdm = tokenApp.getEmail(token);
        Administrador adm = this.admDbRepository.findByEmail(idAdm).get();

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(adm, null, adm.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.substring(7, token.length());
    }
}
