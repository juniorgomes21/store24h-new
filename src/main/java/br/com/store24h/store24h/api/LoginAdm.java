//package br.com.store24h.store24h.api;
//
//import br.com.store24h.store24h.dto.TokenDTO;
//import br.com.store24h.store24h.form.LoginForm;
//import br.com.store24h.store24h.model.Administrador;
//import br.com.store24h.store24h.model.Role;
//import br.com.store24h.store24h.security.TokenApp;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//@RestController
//@RequestMapping("api/ADM")
//public class LoginAdm {
//
//    @Autowired
//    private AuthenticationManager authManager;
//
//    @Autowired
//    private TokenApp tokenApp;
//
//    @PostMapping("/auth/login")
//    public ResponseEntity<TokenDTO> autenticarAdm(@RequestBody @Valid LoginForm form) {
////        UsernamePasswordAuthenticationToken dadosLogin = form.converter();
////
////        try {
////            Authentication authentication = authManager.authenticate(dadosLogin);
////            String token = tokenApp.gerarTokenAdm(authentication);
////
////            return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
////        } catch (AuthenticationException e) {
////            return ResponseEntity.badRequest().build();
////        }
//
//        return null;
//    }
//
//    @GetMapping("/createADM")
//    public ResponseEntity<String> create() {
//
//        Administrador adm = new Administrador();
//
//        adm.setNome("fernando");
//        adm.setEmail("fernando@fernando.com");
//        adm.setPerfil(Role.ADMINISTRADO.getNome());
//        adm.setSenha(new BCryptPasswordEncoder().encode("123"));
//
//        return ResponseEntity.ok("ok");
//    }
//}
