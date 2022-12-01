package br.com.store24h.store24h.api;

import br.com.store24h.store24h.Requisicoes.RequisicaoNovoServico;
import br.com.store24h.store24h.Requisicoes.RequisicaoUpdateService;
import br.com.store24h.store24h.dto.ErrorResponseDto;
import br.com.store24h.store24h.dto.ServicoDto;
import br.com.store24h.store24h.dto.ServicoDtoJunior;
import br.com.store24h.store24h.model.Servico;
import br.com.store24h.store24h.repository.ServicosDbRepository;
import br.com.store24h.store24h.response.ServiceResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/stubs/handler_api/apiServicos")
public class ServicoApi {
    @Autowired
    private ServicosDbRepository servicosRepository;

//    @PostMapping("/newService")
//    public ResponseEntity<ServiceResponse<ServicoDto>> newService(@RequestBody @Valid RequisicaoNovoServico requisicaoNovoServico) {
//        try {
//            Servico serv = requisicaoNovoServico.toServico();
//            return ResponseEntity.ok( { msg: "mensagens", seriv: serv});
//        } catch (Exception e) {
//            ServicoDto lol = new ServicoDto(null);
//            lol.setMsg("Ops! algo deu errado.");
//            return ResponseEntity.badRequest().body(lol);
//        }
//    }

    @GetMapping("/getAllServices")
    public ResponseEntity<Object> getAllServices(@PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 15) Pageable pageable) {
        try {
            Page<Servico> servicoPage = servicosRepository.findAll(pageable);

            return ResponseEntity.ok(servicoPage);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDto("sua senha é imcompátivel!"));
        }
    }

    /***
     * Cira um novo serviço
     * @param requisicaoNovoServico
     * @return
     */
    @PostMapping("/newService")
    public ResponseEntity<Object> newServiceJunior(@RequestBody @Valid RequisicaoNovoServico requisicaoNovoServico) {
        try {
            // caso a senha não seja igaul
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

    @PutMapping("/editService/{id}")
    public ResponseEntity<Object> editService(@PathVariable Long id, @RequestBody RequisicaoUpdateService requisicaoUpdateService) {
        try {
            Optional<Servico> servicoOptional = servicosRepository.findById(id);
            if(!servicoOptional.isPresent()) {
                return ResponseEntity.badRequest().body(new ErrorResponseDto("Serviço não encontrado!"));
            }

            Servico serv = servicoOptional.get();

//            Servico = requisicaoUpdateService.toServico(serv);

            return ResponseEntity.ok(new ServicoDtoJunior(serv));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDto("Ops! algo deu errado."));
        }
    }

    @DeleteMapping("/deleteService/{id}")
    public ResponseEntity<Object> deleteService(@PathVariable Long id) {
        try {
            Optional<Servico> servicoOptional = servicosRepository.findById(id);
            if(!servicoOptional.isPresent()) {
                return ResponseEntity.badRequest().body(new ErrorResponseDto("Serviço não encontrado!"));
            }

            servicosRepository.deleteById(servicoOptional.get().getId());

            return ResponseEntity.ok().build();

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDto("Ops! algo deu errado."));
        }
    }

    @PostMapping("/loadService")
    public ResponseEntity<Object> loadService(@RequestBody Map<String, Servico> requisicaoNovoServicoList) {
        try {
            ArrayList<RequisicaoNovoServico> list = new ArrayList<>(requisicaoNovoServicoList.size());
            for(Servico ls: requisicaoNovoServicoList.values()) {
                RequisicaoNovoServico lol = new RequisicaoNovoServico();
                BeanUtils.copyProperties(ls, lol);
                list.add(lol);
            }

            return ResponseEntity.ok().body(list);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
