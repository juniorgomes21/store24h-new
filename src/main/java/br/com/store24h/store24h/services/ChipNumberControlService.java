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
        ChipNumberControl chipNumberControl = controlRepository.findByChipNumber(chipNumber).get();

        chipNumberControl.getAliasService().add(servico.getAlias());

        controlRepository.save(chipNumberControl);
    }

    public void newChipNumberControl(String chipNumber) {
        ChipNumberControl chipNumberControl = new ChipNumberControl(chipNumber);

        controlRepository.save(chipNumberControl);
    }

    public void removeService(String chipNumber, String aliasService) {
        ChipNumberControl chipNumberControl = controlRepository.findByChipNumber(chipNumber).get();

        chipNumberControl.getAliasService().remove(aliasService);

        controlRepository.save(chipNumberControl);
    }
}
