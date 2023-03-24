package br.com.store24h.store24h.task;

import br.com.store24h.store24h.model.ChipModel;
import br.com.store24h.store24h.model.ChipNumberControl;
import br.com.store24h.store24h.model.Servico;
import br.com.store24h.store24h.model.StatusChipModel;
import br.com.store24h.store24h.repository.ChipNumberControlRepository;
import br.com.store24h.store24h.repository.ChipRepository;
import br.com.store24h.store24h.repository.ServicosRepository;
import br.com.store24h.store24h.services.ChipNumberControlService;
import br.com.store24h.store24h.services.SvsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ServiceTask {

    @Autowired
    private ChipNumberControlRepository controlRepository;

    @Autowired
    private ChipRepository chipRepository;

    @Autowired
    private ServicosRepository servicosRepository;
    @Autowired
    private SvsService svsService;
    @Autowired
    private ChipNumberControlService chipNumberControlService;

    @Scheduled(fixedRate = 120000) // 2min
    public void retellQuantity() {
        List<Servico> servicoList = servicosRepository.findAll();
        List<ChipNumberControl> chipNumberControlList = controlRepository.findAll();

        servicoList.forEach( servico -> {
            servico.setTotalQuantity(0);
        });

        chipNumberControlList.forEach( chipControl -> {
            Optional<ChipModel> chipModelOptional = chipRepository.findByNumber(chipControl.getChipNumber());
            if(chipModelOptional.isPresent() && chipModelOptional.get().getStatus() == 1) {
                servicoList.forEach( servico -> {
                    int index = chipControl.getAliasService().indexOf(servico.getAlias());
                    if(index == -1) {
                        servico.setTotalQuantity(servico.getTotalQuantity() + 1);
                    }
                });
            }
        });

        servicosRepository.saveAll(servicoList);
    }

    @Scheduled(fixedRate = 240000) // 4min
    public void countServiceAddForAll() {

        List<ChipModel> chipModels = chipRepository.findByAtivoAndStatus(false, StatusChipModel.NOACTIVITY.getStatus());
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
                List<Servico> servicesModified = new ArrayList<>();

                servicoList.forEach( servico -> {
                    int index = chipNumberServicosList.indexOf(servico.getAlias());
                    if(index == -1) {
                        servicesModified.add(servico);
                    }
                });

                svsService.addQuantityAllService(servicesModified);

            } else {
                chipNumberControlService.newChipNumberControl(chipModel.getNumber());

                svsService.addQuantityAllService(servicoList);
            }

            chipModel.setChecked(true);
            chipModel.setAtivo(true);
            chipModel.setStatus(StatusChipModel.ACTIVITY.getStatus());
            chipModelModify.add(chipModel);
        });

        chipRepository.saveAll(chipModelModify);
    }

    @Scheduled(fixedRate = 300000)  // 5min
    public void countServiceSubtract() {

        List<ChipModel> chipModels = chipRepository.findByStatusAndChecked(StatusChipModel.INVALID.getStatus(), false);
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

            chipModel.setChecked(true);
        });

        chipRepository.saveAll(chipModels);
        servicosRepository.saveAll(servicos);
    }

}
