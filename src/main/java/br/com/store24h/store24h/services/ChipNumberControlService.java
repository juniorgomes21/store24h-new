package br.com.store24h.store24h.services;

import br.com.store24h.store24h.model.ChipNumberControl;
import br.com.store24h.store24h.model.Servico;
import br.com.store24h.store24h.repository.ChipNumberControlRepository;
import br.com.store24h.store24h.repository.ServicosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChipNumberControlService {

    @Autowired
    private ChipNumberControlRepository controlRepository;

    @Autowired
    private ServicosRepository servicosRepository;

    public void addServiceInNumber(String chipNumber, Servico servico) {
        Optional<ChipNumberControl> chipNumberControlOptional = controlRepository.findByChipNumber(chipNumber);
        ChipNumberControl chipNumberControl;
        if(chipNumberControlOptional.isPresent()) {
            chipNumberControl = chipNumberControlOptional.get();
        } else {
            chipNumberControl = new ChipNumberControl();
            chipNumberControl.setChipNumber(chipNumber);
        }
        chipNumberControl.getAliasService().add(servico.getAlias());
        controlRepository.save(chipNumberControl);
    }
}
