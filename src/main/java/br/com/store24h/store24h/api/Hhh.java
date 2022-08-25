//package br.com.store24h.store24h.api;
//
//import br.com.store24h.store24h.dto.TokenDTO;
//import br.com.store24h.store24h.form.LoginForm;
//import br.com.store24h.store24h.model.Administrador;
//import br.com.store24h.store24h.model.Role;
//import br.com.store24h.store24h.repository.AdmDbRepository;
//import br.com.store24h.store24h.security.TokenApp;
//import com.nimbusds.jose.shaded.json.JSONObject;
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
//
//@RestController
//@RequestMapping("/stubs/handler_api")
//public class Hhh {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private TokenApp tokenApp;
//
//    @Autowired
//    private AdmDbRepository admDbRepository;
//
//    @PostMapping("/auth/login")
//    public ResponseEntity<TokenDTO> autenticarAdm(@RequestBody @Valid LoginForm form) {
//        UsernamePasswordAuthenticationToken dadosLogin = form.converter();
//
//        try {
//            Authentication authentication = authenticationManager.authenticate(dadosLogin);
//            String token = tokenApp.gerarTokenAdm(authentication);
//
//            return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
//        } catch (AuthenticationException e) {
//            return ResponseEntity.badRequest().build();
//        }
//    }
//
//    @GetMapping("/createADM")
//    public ResponseEntity<?> create() {
//        String bcrypt = new BCryptPasswordEncoder().encode("123");
//
//        String[] ar = new String[]{"oi", "tim", "claro"};
////        System.out.println(ar.toString());
//        String st = "";
//        JSONObject myJson = new JSONObject();
//        int count = 1;
//        for (String op :
//                ar) {
//            myJson.put("operadora " + count, op);
//            count ++;
//            System.out.println(op);
//        }
//        return ResponseEntity.ok(myJson);
//    }
//
//
//}
