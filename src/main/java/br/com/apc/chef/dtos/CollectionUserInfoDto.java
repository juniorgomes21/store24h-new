/**
 * 
 */
package br.com.apc.chef.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.apc.chef.entities.Collection;
import br.com.apc.chef.entities.User;

/**
 * @author Archer
 *
 */
public class CollectionUserInfoDto {
	
    private long id;
    
    @NotNull
    private User user;
    
    @NotNull
    private Collection collection;

    boolean[][] matrix;
    
    
	/**
	 * @param user
	 */
	public CollectionUserInfoDto(User user, int size) {
		super();
		this.user = user;
		matrix = new boolean[size][size];
	}



	public long getId() {
		return id;
	}

}
