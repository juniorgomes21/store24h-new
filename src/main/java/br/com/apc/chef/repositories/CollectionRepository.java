package br.com.apc.chef.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apc.chef.entities.Collection;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {

}
