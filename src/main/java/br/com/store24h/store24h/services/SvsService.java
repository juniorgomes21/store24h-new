package br.com.store24h.store24h.services;

import br.com.store24h.store24h.Requisicoes.RequisicaoNovoServico;
import br.com.store24h.store24h.model.CompraServiso;
import br.com.store24h.store24h.model.Servico;
import br.com.store24h.store24h.repository.BuyServiceRepository;
import br.com.store24h.store24h.repository.ServicosRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SvsService {

    @Autowired
    private ServicosRepository servicosRepository;

    @Autowired
    private BuyServiceRepository buyServiceRepository;

    @Autowired
    private ChipService chipService;

    public List<Servico> getAllServices() {
        Sort sort = Sort.by("name");
        List<Servico> servicoList = servicosRepository.findAll(sort);

        return servicoList;
    }

    public String deleteService(Long id) {
        Optional<Servico> servicoOptional = servicosRepository.findById(id);
        if(!servicoOptional.isPresent()) {
            return null;
        }

        servicosRepository.deleteById(servicoOptional.get().getId());

        return "ok";
    }

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

    public void editActivityServices(List<String> aliasServices) {
        List<Servico> servicoList = servicosRepository.findByAliasIn(aliasServices);

        servicoList.forEach( servico -> {
            servico.setActivity(!servico.isActivity());
        });

        servicosRepository.saveAll(servicoList);
    }

    public void editPriceService(Long id, BigDecimal newPrice) {
        Servico servico = servicosRepository.findById(id).get();
        servico.setPrice(newPrice);

        servicosRepository.save(servico);
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

    public void addQuantityAllService(List<Servico> servicoList) {
        servicoList.forEach( service -> {
            service.setTotalQuantity(service.getTotalQuantity() + 1);
        });

        servicosRepository.saveAll(servicoList);
    }

    public void saveRegisterBuy(Long idActivation, Servico servico, String number, Long idUser) {
        CompraServiso compraServiso = new CompraServiso(idActivation, servico.getAlias(), number, servico.getPrice(), idUser);
        buyServiceRepository.save(compraServiso);
    }
}
