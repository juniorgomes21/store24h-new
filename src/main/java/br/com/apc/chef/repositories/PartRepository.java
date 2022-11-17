package br.com.apc.chef.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apc.chef.entities.Part;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {

}
