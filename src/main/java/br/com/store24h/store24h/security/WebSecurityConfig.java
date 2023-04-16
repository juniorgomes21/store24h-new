package br.com.store24h.store24h.security;

import br.com.store24h.store24h.model.Role;
import br.com.store24h.store24h.repository.AdmDbRepository;
import br.com.store24h.store24h.repository.UserDbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TokenApp tokenApp;

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private UserDbRepository jogadorRepository;

    @Autowired
    private AdmDbRepository admRepository;

    //Conficaração para o JWT
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception{
        http
                .cors()
                .and()
                    .authorizeRequests()
                //Auxiliar
                .antMatchers("/aux/delete/registros/acivations&chipModelFalse").permitAll()
                .antMatchers("/aux/setQuantityFor0AllService").permitAll()
                .antMatchers("/aux/verifyInvalidChipNumber").permitAll()
                .antMatchers("/aux/verifyNewChipNumber").permitAll()
                .antMatchers("/aux/cancelAPI").permitAll()

                //Padrão API ADM
                .antMatchers("/stubs/handler_api/ping").permitAll()
                .antMatchers("/stubs/handler_api/getSms").permitAll()
                .antMatchers("/stubs/handler_api/conclude/activation/**").permitAll()
                .antMatchers("/stubs/handler_api/cancel/activation/**").permitAll()
                .antMatchers("/stubs/handler_api/auth/login").permitAll()
                .antMatchers("/stubs/handler_api/createADM").permitAll()
                .antMatchers("/stubs/handler_api/createKeyApiMD5").permitAll()
                .antMatchers("/stubs/handler_api/createUser").permitAll()
                .antMatchers("/stubs/handler_api/auth/login/user").permitAll()
                .antMatchers("/stubs/handler_api/getNumberStatux").permitAll()
                .antMatchers("/stubs/handler_api/getBalance").permitAll()
                .antMatchers("/stubs/handler_api/getNumber").permitAll()
                .antMatchers("/stubs/handler_api/getNumberStatus").permitAll()
                .antMatchers("/stubs/handler_api/status").permitAll()
                .antMatchers("/stubs/handler_api/setStatus").permitAll()
                .antMatchers("/stubs/handler_api/prices").permitAll()
                .antMatchers("/stubs/handler_api/listaDePaisesOperadoras/**").permitAll()
                .antMatchers("/stubs/handler_api/listServicos").permitAll()
                .antMatchers("/stubs/handler_api/activations/status").permitAll()
                .antMatchers("/stubs/handler_api/activations/valids").permitAll()
                .antMatchers("/stubs/handler_api/activations/history").permitAll()

                .antMatchers("/stubs/handler_api/testartoken").hasAuthority(Role.USER.getNome())
                .antMatchers("/stubs/handler_api/userDetails").hasAuthority(Role.USER.getNome())
                .antMatchers("/stubs/handler_api/criarChaveApi").hasAuthority(Role.USER.getNome())

                //services
                .antMatchers("/stubs/handler_api/apiServicos/newService").permitAll()
                .antMatchers("/stubs/handler_api/apiServicos/edit/price/**").hasAuthority(Role.USER.getNome())
                .antMatchers("/stubs/handler_api/apiServicos/loadService").permitAll()
                .antMatchers("/stubs/handler_api/apiServicos/getAllServices").permitAll()
                .antMatchers("/stubs/handler_api/apiServicos/getAllServices/hub").permitAll()
                .antMatchers("/stubs/handler_api/apiServicos/setActivityServices/hub").permitAll()
                .antMatchers("/stubs/handler_api/apiServicos/getAllServicesX").permitAll()
                .antMatchers("/stubs/handler_api/apiServicos/getService/**").permitAll()
                .antMatchers("/stubs/handler_api/apiServicos/editService/**").permitAll()
                .antMatchers("/stubs/handler_api/apiServicos/deleteService/**").permitAll()
                .antMatchers("/stubs/handler_api/apiServicos/comprarServico/**").permitAll()
                .antMatchers("/stubs/handler_api/apiServicos/getComprasFeitas").permitAll()

                .antMatchers("/stubs/handler_api/getTableCredito").permitAll()
                .antMatchers("/stubs/handler_api/getCredito").hasAuthority(Role.USER.getNome())
                .antMatchers("/stubs/handler_api/comprarCredito").hasAuthority(Role.USER.getNome())
                .antMatchers("/stubs/handler_api/edit/password").hasAuthority(Role.USER.getNome())
                .antMatchers("/stubs/handler_api/getApiKey").hasAuthority(Role.USER.getNome())
                //Padrão API CLIENTE
                .antMatchers("/stubs/handler_api").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .addFilterBefore(new AutheticacaoViaTokenFilter(tokenApp, jogadorRepository, admRepository), UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
    }
}

