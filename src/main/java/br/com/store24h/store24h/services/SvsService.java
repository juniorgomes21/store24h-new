package br.com.store24h.store24h.services;

import br.com.store24h.store24h.model.Activation;
import br.com.store24h.store24h.model.ChipModel;
import br.com.store24h.store24h.model.ChipNumberControl;
import br.com.store24h.store24h.model.Servico;
import br.com.store24h.store24h.repository.ActivationRepository;
import br.com.store24h.store24h.repository.ChipNumberControlRepository;
import br.com.store24h.store24h.repository.ChipRepository;
import br.com.store24h.store24h.repository.ServicosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SvsService {

    @Autowired
    private ChipNumberControlRepository controlRepository;

    @Autowired
    private ChipRepository chipRepository;

    @Autowired
    private ServicosRepository servicosRepository;


    public void countService() {

        List<ChipModel> chipModels = chipRepository.findByAtivo(false);
        if(chipModels.isEmpty()) {
            return;
        }

        List<Servico> servicoList = servicosRepository.findAll();
        List<ChipModel> chipModelModify = new ArrayList<>();

        chipModels.forEach( chipModel -> {
            Optional<ChipNumberControl> chipNumberControlOptional = controlRepository.findByChipNumber(chipModel.getNumber());

            if(chipNumberControlOptional.isPresent()) {
                ChipNumberControl chipNumberControl = chipNumberControlOptional.get();

                List<Servico> chipNumberServicosList = chipNumberControl.getServicos();

                if(servicoList.size() > chipNumberServicosList.size()) {
                    // lógica para count +1 em serviço
                    servicoList.forEach( service -> {
                        if(!chipNumberServicosList.contains(service)) {
                            service.setTotalQuantity(service.getTotalQuantity() + 1);
                        }
                    });

                    chipModel.setAtivo(true);
                    chipModelModify.add(chipModel);
                }
            } else {
                servicoList.forEach(service -> {
                    service.setTotalQuantity(service.getTotalQuantity() + 1);
                });
                chipModel.setAtivo(true);
                chipModelModify.add(chipModel);
            }

        });

        servicosRepository.saveAll(servicoList);

    }

    public void subtractQuantity(Servico service) {
        service.setTotalQuantity(service.getTotalQuantity() - 1);
        servicosRepository.save(service);
    }

    public void addQuantity(String serviceName) {
        Servico service = servicosRepository.findByName(serviceName).get();
        service.setTotalQuantity(service.getTotalQuantity() + 1);
        servicosRepository.save(service);
    }
}
