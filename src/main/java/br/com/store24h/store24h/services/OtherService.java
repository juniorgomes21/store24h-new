package br.com.store24h.store24h.services;

import br.com.store24h.store24h.model.ChipOther;
import br.com.store24h.store24h.repository.ChipOtherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OtherService {

    @Autowired
    private ChipOtherRepository otherRepository;

    public boolean isIn(String chipNumber) {

        Optional<ChipOther> otherOptional = otherRepository.findByNumber(chipNumber);

        return otherOptional.isPresent();
    }

    public void remove(String chipNumber) {
        otherRepository.deleteByNumber(chipNumber);
    }

    public ChipOther save(String number) {
        ChipOther other = new ChipOther();
        other.setNumber(number);

        return otherRepository.save(other);
    }
}
