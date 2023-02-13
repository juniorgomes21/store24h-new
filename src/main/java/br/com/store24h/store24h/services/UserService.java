package br.com.store24h.store24h.services;

import br.com.store24h.store24h.model.User;
import br.com.store24h.store24h.repository.ServicosRepository;
import br.com.store24h.store24h.repository.UserDbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDbRepository userDbRepository;

    @Autowired
    private ServicosRepository servicosRepository;

    public User userLogado(Authentication authentication) {
        User user = null;
        if(authentication.getPrincipal() instanceof User) {
            user = (User) authentication.getPrincipal();
        }

        return user;
    }

    public void updatePassword(Authentication authentication, String currentPassword) {
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        String newPassword = bc.encode(currentPassword);

        User user = this.userLogado(authentication);
        user.setSenha(newPassword);

        userDbRepository.save(user);
    }

    public boolean testPassword(String password, Authentication authentication) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = this.userLogado(authentication);

        return passwordEncoder.matches(password, user.getPassword());
    }

    public boolean isValidApiKey(String apiKey) {

        return userDbRepository.findByApiKey(apiKey).isPresent();
    }
}
