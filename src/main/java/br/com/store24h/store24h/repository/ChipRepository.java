/**
 * 
 */
package br.com.store24h.store24h.repository;


import br.com.store24h.store24h.model.ChipModel;
import br.com.store24h.store24h.model.SmsModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Archer
 *
 */
public interface ChipRepository extends JpaRepository<ChipModel, Long> {
    Optional<List<SmsModel>> findByAlugado(Boolean value);
    Optional<List<SmsModel>> findByAtivo(Boolean value);
    Optional<List<SmsModel>> findByAlugadoAndAtivo(Boolean alugado, Boolean ativo);
    Optional<List<SmsModel>> findByAlugadoAndAtivoAndOperadora(Boolean alugado, Boolean ativo, String operator);
}
