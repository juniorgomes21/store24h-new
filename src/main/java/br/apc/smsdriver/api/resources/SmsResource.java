package br.apc.smsdriver.api.resources;

import br.apc.smsdriver.api.dtos.SmsDto;
import br.apc.smsdriver.api.repositories.SmsRepository;
import br.apc.smsdriver.api.services.UniversalService;
import br.apc.smsdriver.entities.SmsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("sms")
public class SmsResource {

    @Autowired
    private SmsRepository smsRepository;
    private UniversalService universalService;

    @GetMapping
    public ResponseEntity<List<SmsModel>> findAll(){
        return ResponseEntity.ok().body(smsRepository.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SmsModel> findById(@PathVariable Long id){

        return ResponseEntity.ok().body(smsRepository.findById(id).get());
    }

    @PostMapping
    public ResponseEntity<SmsModel> create(@RequestBody @Valid SmsDto smsDto) {
        SmsModel smsModel = universalService.createSms(smsDto);
        return ResponseEntity.ok().body(smsModel);
    }
}
