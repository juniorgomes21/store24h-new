package br.com.store24h.store24h.security;
import br.com.store24h.store24h.model.Administrador;
import br.com.store24h.store24h.model.User;
import br.com.store24h.store24h.repository.AdmDbRepository;
import br.com.store24h.store24h.repository.UserDbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service("userDetailsService")
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private AdmDbRepository admRepository;

    @Autowired
    private UserDbRepository userDbRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> jogador = userDbRepository.findByEmail(username);
        Optional<Administrador> adm = admRepository.findByEmail(username);

        if (jogador.isPresent()) {
            User jogadorLogado = jogador.get();
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(jogadorLogado.getPerfil());
            Set<GrantedAuthority> authorities = new HashSet<>();
            authorities.add(authority);

            return jogadorLogado;

        } else if (adm.isPresent()) {
            Administrador administrador = adm.get();
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(administrador.getPerfil());
            Set<GrantedAuthority> authorities = new HashSet<>();
            authorities.add(authority);

            return administrador;
        }
        else {
            throw new UsernameNotFoundException("dados invalidos");
        }
    }
}
