package br.apc.smsdriver.api.resources;

import br.apc.smsdriver.api.dtos.ModemDto;
import br.apc.smsdriver.api.repositories.ModemRepository;
import br.apc.smsdriver.api.services.UniversalService;
import br.apc.smsdriver.entities.ModemModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("modem")
public class ModemResource {

    @Autowired
    private ModemRepository modemRepository;

    @GetMapping
    public ResponseEntity<List<ModemModel>> findAll(){
        return ResponseEntity.ok().body(modemRepository.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ModemModel> findById(@PathVariable Long id){

        return ResponseEntity.ok().body(modemRepository.findById(id).get());
    }

    @PostMapping
    public ResponseEntity<ModemModel> create(@RequestBody @Valid ModemDto modemDto) {
        ModemModel modemModel = new UniversalService().createModem(modemDto);
        return ResponseEntity.ok().body(modemModel);
    }
}
