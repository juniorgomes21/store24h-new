package br.com.apc.chef.repositories;

import br.com.apc.chef.entities.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Administrador, Long> {
}
