package br.apc.smsdriver.api.services;

import br.apc.smsdriver.api.dtos.ModemDto;
import br.apc.smsdriver.api.dtos.SmsDto;
import br.apc.smsdriver.api.repositories.ChipRepository;
import br.apc.smsdriver.api.repositories.ModemRepository;
import br.apc.smsdriver.api.repositories.SmsRepository;
import br.apc.smsdriver.entities.ChipModel;
import br.apc.smsdriver.entities.ModemModel;
import br.apc.smsdriver.entities.SmsModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UniversalService {

    @Autowired
    private ChipRepository chipRepository;
    @Autowired
    private ModemRepository modemRepository;
    @Autowired
    private SmsRepository smsRepository;

    @Transactional
    public ChipModel createChip(br.apc.smsdriver.api.dtos.ChipDto dto) {

        ChipModel chipModel = new ChipModel();
        BeanUtils.copyProperties(dto, chipModel);

        return chipRepository.save(chipModel);
    }

    @Transactional
    public ModemModel createModem(ModemDto dto) {

        ModemModel modemModel = new ModemModel();
        BeanUtils.copyProperties(dto, modemModel);

        return modemRepository.save(modemModel);

    }

    @Transactional
    public SmsModel createSms(SmsDto dto) {
        SmsModel smsModel = new SmsModel();
        BeanUtils.copyProperties(dto, smsModel);

        return smsRepository.save(smsModel);

    }

}
