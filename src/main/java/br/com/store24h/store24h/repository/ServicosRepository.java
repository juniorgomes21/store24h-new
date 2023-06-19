package br.com.store24h.store24h.repository;

import br.com.store24h.store24h.model.Servico;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServicosRepository extends JpaRepository<Servico, Long> {

    Optional<Servico> findByAlias(String aliasService);
    Optional<Servico> findByName(String serviceName);
    List<Servico> findByAliasIn(List<String> aliasServices);

    List<Servico> findByActivity(boolean b , Sort name);
}
