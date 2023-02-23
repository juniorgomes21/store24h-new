package br.com.store24h.store24h.services;

import br.com.store24h.store24h.Requisicoes.RequisicaoNovoServico;
import br.com.store24h.store24h.dto.ErrorResponseDto;
import br.com.store24h.store24h.model.ChipModel;
import br.com.store24h.store24h.model.ChipNumberControl;
import br.com.store24h.store24h.model.Servico;
import br.com.store24h.store24h.model.StatusChipModel;
import br.com.store24h.store24h.repository.ChipNumberControlRepository;
import br.com.store24h.store24h.repository.ChipRepository;
import br.com.store24h.store24h.repository.ServicosRepository;
import br.com.store24h.store24h.task.service.ServiceTask;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SvsService {

    @Autowired
    private ServicosRepository servicosRepository;

    @Autowired
    private ChipService chipService;

    @Autowired
    private ServiceTask serviceTask;

    public void countServiceAddForAll() {
        serviceTask.countServiceAddForAll();
    }

    public void countServiceSubtract() {
        serviceTask.countServiceSubtract();
    }

    @Cacheable(value="servicesHub")
    public List<Servico> getAllServices() {
        Sort sort = Sort.by("name");
        List<Servico> servicoList = servicosRepository.findAll(sort);

        return servicoList;
    }

    @CacheEvict(value="servicesHub", allEntries=true)
    public String deleteService(Long id) {
        Optional<Servico> servicoOptional = servicosRepository.findById(id);
        if(!servicoOptional.isPresent()) {
            return null;
        }

        servicosRepository.deleteById(servicoOptional.get().getId());

        return "ok";
    }

    @CacheEvict(value="servicesHub", allEntries=true)
    public List<Servico> loadService(Map<String, Servico> requisicaoNovoServicoList) {
        ArrayList<Servico> list = new ArrayList<>(requisicaoNovoServicoList.size());
        for(Servico ls: requisicaoNovoServicoList.values()) {
            RequisicaoNovoServico lol = new RequisicaoNovoServico();
            BeanUtils.copyProperties(ls, lol);
            list.add(ls);
        }

        servicosRepository.saveAll(list);

        return list;
    }

    @CacheEvict(value="servicesHub", allEntries=true)
    public void setActivityServices(List<String> aliasServices) {
        List<Servico> servicoList = servicosRepository.findByAliasIn(aliasServices);

        servicoList.forEach( servico -> {
            servico.setActivity(!servico.isActivity());
        });

        servicosRepository.saveAll(servicoList);
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
