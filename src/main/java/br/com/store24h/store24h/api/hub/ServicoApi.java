package br.com.store24h.store24h.api.hub;

import br.com.store24h.store24h.Requisicoes.ParamActivity;
import br.com.store24h.store24h.Requisicoes.ParamDate;
import br.com.store24h.store24h.Requisicoes.RequisicaoNovoServico;
import br.com.store24h.store24h.Requisicoes.RequisicaoUpdateService;
import br.com.store24h.store24h.Requisicoes.services.ParamEditPriceSevice;
import br.com.store24h.store24h.dto.CompraServisoDTO;
import br.com.store24h.store24h.dto.ErrorResponseDto;
import br.com.store24h.store24h.dto.ServicoDtoJunior;
import br.com.store24h.store24h.model.CompraServiso;
import br.com.store24h.store24h.model.Servico;
import br.com.store24h.store24h.model.User;
import br.com.store24h.store24h.repository.BuyServiceRepository;
import br.com.store24h.store24h.repository.ServicosRepository;
import br.com.store24h.store24h.repository.UserDbRepository;
import br.com.store24h.store24h.services.SvsService;
import br.com.store24h.store24h.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/stubs/handler_api/apiServicos")
public class ServicoApi {
    @Autowired
    private ServicosRepository servicosRepository;

    @Autowired
    private UserDbRepository userDbRepository;

    @Autowired
    private BuyServiceRepository buyServiceRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private SvsService svsService;


    @GetMapping("/activity")
    public ResponseEntity<Object> getAllServicesActivity() {
        try {
            List<Servico> servicoPage = servicosRepository.findByActivity(true, Sort.by(Sort.Direction.ASC, "name"));

            return ResponseEntity.ok(servicoPage);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDto());
        }
    }


    @GetMapping("/get/all/services")
    public ResponseEntity<Object> getAllServicesX() {
        try {
            Sort sort = Sort.by("name");
            List<Servico> servicoPage = servicosRepository.findAll(sort);

            return ResponseEntity.ok(servicoPage);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDto("sua senha é imcompátivel!"));
        }
    }


    @PostMapping("/edit/price/{id}")
    public ResponseEntity<Object> editPrice(@PathVariable Long id, @RequestBody ParamEditPriceSevice paramEditPrice) {
        try {

            svsService.editPriceService(id, paramEditPrice.getNewPrice());

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDto());
        }
    }


    @PostMapping("/setActivity/services")
    public ResponseEntity<Object> setActivityServices(@RequestBody ParamActivity paramActivity) {
        try {
            svsService.editActivityServices(paramActivity.getAliasServices());

            return ResponseEntity.ok().build();

        } catch (Exception e) {

            return ResponseEntity.badRequest().build();
        }
    }


    /***
     * Cira um novo serviço
     * @param requisicaoNovoServico
     * @return
     */
    @CacheEvict("services")
    @PostMapping("/newService")
    public ResponseEntity<Object> newServiceJunior(@RequestBody @Valid RequisicaoNovoServico requisicaoNovoServico) {
        try {
            if(false) {
                return ResponseEntity.badRequest().body(new ErrorResponseDto("sua senha é imcompátivel!"));
            }

            Servico serv = requisicaoNovoServico.toServico();
            servicosRepository.save(serv);

            return ResponseEntity.ok().body(new ServicoDtoJunior(serv));
        } catch (Exception e) {

            return ResponseEntity.badRequest().body(new ErrorResponseDto("Ops! algo deu errado."));
        }
    }

    /***
     * Pega um serviço existente
     * @param id
     * @return
     */
    @GetMapping("/getService/{id}")
    public ResponseEntity<Object> getServiceJunior(@PathVariable Long id) {
        try {
            Optional<Servico> servicoOptional = servicosRepository.findById(id);
            if(!servicoOptional.isPresent()) {
                return ResponseEntity.badRequest().body(new ErrorResponseDto("Serviço não encontrado!"));
            }

            Servico serv = servicoOptional.get();

            return ResponseEntity.ok(new ServicoDtoJunior(serv));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDto("Ops! algo deu errado."));
        }
    }

    @CacheEvict("services")
    @PostMapping("/editService/{id}") //TODO apagar
    public ResponseEntity<Object> editService(@PathVariable Long id, @RequestBody RequisicaoUpdateService requisicaoUpdateService) {
        try {
            Optional<Servico> servicoOptional = servicosRepository.findById(id);
            if(!servicoOptional.isPresent()) {
                return ResponseEntity.badRequest().body(new ErrorResponseDto("Serviço não encontrado!"));
            }

            Servico serv = servicoOptional.get();
            serv.setPrice(requisicaoUpdateService.getPrice());

            servicosRepository.save(serv);

            return ResponseEntity.ok(new ServicoDtoJunior(serv));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDto("Ops! algo deu errado."));
        }
    }

    @DeleteMapping("/deleteService/{id}")
    public ResponseEntity<Object> deleteService(@PathVariable Long id) {
        try {
            String response = svsService.deleteService(id);

            if(response == null) {
                return ResponseEntity.badRequest().body(new ErrorResponseDto("Serviço não encontrado!"));
            }

            return ResponseEntity.ok().build();

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDto("Ops! algo deu errado."));
        }
    }

    @PostMapping("/loadService")
    public ResponseEntity<Object> loadService(@RequestBody Map<String, Servico> requisicaoNovoServicoList) {
        try {

            List<Servico> list = svsService.loadService(requisicaoNovoServicoList);

            return ResponseEntity.ok().body(list);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/getComprasFeitas")
    public ResponseEntity<Object> comprasFeitasHub(@PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable, Authentication authentication) {

        Page<CompraServiso> compraServisoPage = buyServiceRepository.findAll(pageable);

        return ResponseEntity.ok(compraServisoPage);
    }

    @PostMapping("/getComprasFeitas/hub/filter")
    public ResponseEntity<Object> comprasFeitasFilter(@RequestBody ParamDate paramDate, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable) {

        LocalDateTime dateInitial = this.auxDate(paramDate.getDateInitial());
        LocalDateTime datefinal = this.auxDate(paramDate.getDateFinal());

        Page<CompraServiso> compraServisoPage = buyServiceRepository.findByLocalDateTimeBetween(dateInitial, datefinal, pageable);

        return ResponseEntity.ok(compraServisoPage);
    }

    private LocalDateTime auxDate(String date) {
        int year = Integer.parseInt(date.substring(11, 15));
        String monthStrig = date.substring(4, 7).toUpperCase();
        if(monthStrig.equals("JAN")) {
            monthStrig = "JANUARY";
        } else if (monthStrig.equals("FEB")) {
            monthStrig = "FEBRUARY";
        } else if (monthStrig.equals("MAR")) {
            monthStrig = "MARCH";
        } else if (monthStrig.equals("APR")) {
            monthStrig = "APRIL";
        } else if (monthStrig.equals("MAY")) {
            monthStrig = "MAY";
        } else if (monthStrig.equals("JUN")) {
            monthStrig = "JUNE";
        } else if (monthStrig.equals("JUL")) {
            monthStrig = "JULY";
        } else if (monthStrig.equals("AUG")) {
            monthStrig = "AUGUST";
        } else if (monthStrig.equals("SEP")) {
            monthStrig = "SEPTEMBER";
        }else if (monthStrig.equals("OCT")) {
            monthStrig = "OCTOBER";
        } else if (monthStrig.equals("NOV")) {
            monthStrig = "NOVEMBER";
        } else {
            monthStrig = "DECEMBER";
        }
        Month month = Month.valueOf(monthStrig);
        int day = Integer.parseInt(date.substring(8, 10));
        int hours = Integer.parseInt(date.substring(16, 18));
        int minutes = Integer.parseInt(date.substring(19, 21));

        LocalDateTime dateOk = LocalDateTime.of(year, month, day, hours, minutes);

        return dateOk;
    }
}
