package br.apc.smsdriver.api.resources;

import br.apc.smsdriver.api.dtos.ChipDto;
import br.apc.smsdriver.api.repositories.ChipRepository;
import br.apc.smsdriver.api.services.UniversalService;
import br.apc.smsdriver.delta.one.eventhandler.NumberHandler;
import br.apc.smsdriver.delta.one.eventhandler.OperadoraDtoHandler;
import br.apc.smsdriver.delta.one.eventhandler.OperadoraHandler;
import br.apc.smsdriver.entities.ChipModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("chip")
public class ChipResource {

    @Autowired
    private ChipRepository chipRepository;
    @Autowired
    private UniversalService universalService;

    @GetMapping
    public ResponseEntity<List<ChipModel>> findAll(){
        return ResponseEntity.ok().body(chipRepository.findAll());
    }

    @GetMapping(value = "/numbers")
    public ResponseEntity<Map<String, String>> findNumbers(){
        return ResponseEntity.ok().body(NumberHandler.data);
    }

    @GetMapping(value = "/operadoras")
    public ResponseEntity<Map<String, String>> findOperadoras(){
        return ResponseEntity.ok().body(OperadoraHandler.data);
    }

    @GetMapping(value = "/operadorasdtos")
    public ResponseEntity<Map<String, ChipDto>> findOperadorasDtos(){
        return ResponseEntity.ok().body(OperadoraDtoHandler.data);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ChipModel> findById(@PathVariable Long id){

        return ResponseEntity.ok().body(chipRepository.findById(id).get());
    }

    @PostMapping
    public ResponseEntity<ChipModel> create(@RequestBody @Valid ChipDto chipdto) {
        ChipModel chipModel = universalService.createChip(chipdto);
        return ResponseEntity.ok().body(chipModel);
    }

}
