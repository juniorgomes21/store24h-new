package br.com.store24h.store24h.api;

import br.com.store24h.store24h.model.Activation;
import br.com.store24h.store24h.model.ChipModel;
import br.com.store24h.store24h.repository.ActivationRepository;
import br.com.store24h.store24h.repository.ChipRepository;
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
}
