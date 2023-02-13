package br.com.store24h.store24h.api;

import br.com.store24h.store24h.model.ChipModel;
import br.com.store24h.store24h.model.Servico;
import br.com.store24h.store24h.repository.ActivationRepository;
import br.com.store24h.store24h.repository.ChipRepository;
import br.com.store24h.store24h.repository.ServicosRepository;
import br.com.store24h.store24h.services.SvsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/aux")
public class Auxiliar {

    @Autowired
    private ActivationRepository activationRepository;

    @Autowired
    private ChipRepository chipRepository;

    @Autowired
    private ServicosRepository servicosRepository;

    @Autowired
    private SvsService svsService;

    @Transactional
    @PostMapping("/delete/registros/acivations&chipModelFalse")
    public ResponseEntity<Object> delete() {
        try {
            activationRepository.deleteAll();

            List<ChipModel> chipModelList = chipRepository.findAll();
            List<ChipModel> chipModels = new ArrayList<>();

            for(ChipModel cm: chipModelList) {
                cm.setAlugado(false);
                chipModels.add(cm);
            }

            chipRepository.saveAll(chipModels);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/setQuantityFor0AllService")
    public ResponseEntity<Object> setQuantityFor0AllService() {
        List<Servico> servicoList = servicosRepository.findAll();

        servicoList.forEach( servico -> {
            servico.setTotalQuantity(0);
        });

        servicosRepository.saveAll(servicoList);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/verifyNewChipNumber")
    public ResponseEntity<Object> verifyNewChipNumber() {

        svsService.countServiceAdd();

        return ResponseEntity.ok().build();
    }
}
