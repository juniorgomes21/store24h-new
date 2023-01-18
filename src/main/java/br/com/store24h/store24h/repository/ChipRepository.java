/**
 * 
 */
package br.com.store24h.store24h.repository;


import br.com.store24h.store24h.model.ChipModel;
import br.com.store24h.store24h.model.SmsModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Archer
 *
 */
public interface ChipRepository extends JpaRepository<ChipModel, Long> {
    ChipModel findByNumber(String number);
    Optional<List<ChipModel>> findByAlugado(Boolean value);
    Optional<List<ChipModel>> findByAtivo(Boolean value);
    Optional<List<ChipModel>> findByAlugadoAndAtivo(Boolean alugado, Boolean ativo);
    List<ChipModel> findByAlugadoAndAtivo(Boolean alugado, Boolean ativo, Pageable pageable);
    Optional<List<ChipModel>> findByAlugadoAndAtivoAndOperadora(Boolean alugado, Boolean ativo, String operator);
    List<ChipModel> findByAlugadoAndAtivoAndOperadora(Boolean alugado, Boolean ativo, String operator, Pageable pageable);

}
