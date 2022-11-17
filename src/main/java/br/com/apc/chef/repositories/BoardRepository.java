package br.com.apc.chef.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apc.chef.entities.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

}
