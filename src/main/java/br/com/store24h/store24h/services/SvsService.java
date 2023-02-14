package br.com.store24h.store24h.services;

import br.com.store24h.store24h.model.ChipModel;
import br.com.store24h.store24h.model.ChipNumberControl;
import br.com.store24h.store24h.model.Servico;
import br.com.store24h.store24h.model.StatusChipModel;
import br.com.store24h.store24h.repository.ChipNumberControlRepository;
import br.com.store24h.store24h.repository.ChipRepository;
import br.com.store24h.store24h.repository.ServicosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SvsService {

    @Autowired
    private ChipNumberControlRepository controlRepository;

    @Autowired
    private ChipRepository chipRepository;

    @Autowired
    private ServicosRepository servicosRepository;

    @Autowired
    private ChipService chipService;

    @Scheduled(fixedRate = 240000) // 4min
    public void countServiceAddForAll() {

        List<ChipModel> chipModels = chipRepository.findByAtivoAndStatus(false, 0);
        if(chipModels.isEmpty()) {
            return;
        }

        List<Servico> servicoList = servicosRepository.findAll();
        List<ChipModel> chipModelModify = new ArrayList<>();

        chipModels.forEach( chipModel -> {
            Optional<ChipNumberControl> chipNumberControlOptional = controlRepository.findByChipNumber(chipModel.getNumber());

            if(chipNumberControlOptional.isPresent()) {
                ChipNumberControl chipNumberControl = chipNumberControlOptional.get();

                List<String> chipNumberServicosList = chipNumberControl.getAliasService();

                if(servicoList.size() > chipNumberServicosList.size()) {
                    // lógica para count +1 em serviço
                    servicoList.forEach( service -> {
                        if(!chipNumberServicosList.contains(service.getAlias())) {
                            service.setTotalQuantity(service.getTotalQuantity() + 1);
                        }
                    });

                    chipModel.setAtivo(true);
                    chipModel.setStatus(StatusChipModel.ACTIVITY.getStatus());
                    chipModelModify.add(chipModel);
                }
            } else {
                servicoList.forEach(service -> {
                    service.setTotalQuantity(service.getTotalQuantity() + 1);
                });

                chipModel.setAtivo(true);
                chipModel.setStatus(StatusChipModel.ACTIVITY.getStatus());
                chipModelModify.add(chipModel);
            }

        });

        chipRepository.saveAll(chipModelModify);
        servicosRepository.saveAll(servicoList);

    }

    @Scheduled(fixedRate = 300000)  // 5min
    public void countServiceSubtract() {

        List<ChipModel> chipModels = chipRepository.findByStatus(StatusChipModel.INVALID.getStatus());
        if(chipModels.isEmpty()) {
            return;
        }

        List<ChipNumberControl> chipNumberControls = controlRepository.findAll();
        List<Servico> servicos = servicosRepository.findAll();

        chipModels.forEach( chipModel -> {
            Optional<ChipNumberControl> controlOptional = chipNumberControls.stream()
                    .filter(control -> control.getChipNumber().equals(chipModel.getNumber()))
                    .findFirst();

            if (controlOptional.isPresent()) {
                ChipNumberControl control = controlOptional.get();
                List<String> servicosControl = control.getAliasService();
                List<Servico> servicosFiltered = servicos.stream().filter(servico ->
                                servicosControl.stream().noneMatch(servicoControl ->
                                        servicoControl.equals(servico.getAlias())))
                        .collect(Collectors.toList());

                servicosFiltered.forEach( servicoFiltered -> {
                    Optional<Servico> servicoOptinal = servicos.stream()
                            .filter(servico -> servico.getAlias().equals(servicoFiltered.getAlias()))
                            .findFirst();

                    if (servicoOptinal.isPresent()) {
                        Servico s = servicoOptinal.get();
                        int quantity = s.getTotalQuantity();
                        if(quantity > 0) {
                            s.setTotalQuantity(quantity - 1);
                        }
                    }
                });
            } else {
                servicos.forEach(servico -> {
                    int quantity = servico.getTotalQuantity();
                    if(quantity > 0) {
                        servico.setTotalQuantity(quantity - 1);
                    }
                });
            }
        });

        servicosRepository.saveAll(servicos);
    }

    public void subtractQuantityFor0All() {
        List<Servico> servicoList = servicosRepository.findAll();

        servicoList.forEach( servico -> {
            servico.setTotalQuantity(0);
        });

        servicosRepository.saveAll(servicoList);

        chipService.reset();
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
