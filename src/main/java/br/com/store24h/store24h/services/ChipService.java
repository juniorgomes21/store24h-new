package br.com.store24h.store24h.services;

import br.com.store24h.store24h.model.ChipModel;
import br.com.store24h.store24h.model.StatusChipModel;
import br.com.store24h.store24h.repository.ChipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChipService {

    @Autowired
    private ChipRepository chipRepository;

    public void activityChips(List<ChipModel> chipModelList) {

        List<ChipModel> chipModelModify = new ArrayList<>();

        chipModelList.forEach( chipModel -> {
            chipModel.setAtivo(true);
            chipModel.setStatus(StatusChipModel.ACTIVITY.getStatus());
            chipModelModify.add(chipModel);
        });

        chipRepository.saveAll(chipModelModify);
    }

    public void reset() {
        List<ChipModel> chipModelList = chipRepository.findAll();
        List<ChipModel> chipModelModify = new ArrayList<>();

        chipModelList.forEach( chipModel -> {
            chipModel.setAtivo(false);
            chipModel.setStatus(StatusChipModel.NOACTIVITY.getStatus());
            chipModelModify.add(chipModel);
        });

        chipRepository.saveAll(chipModelList);
    }
}
