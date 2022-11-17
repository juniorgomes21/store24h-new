/**
 * 
 */
package br.com.apc.chef.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apc.chef.entities.User;

/**
 * @author Archer
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
