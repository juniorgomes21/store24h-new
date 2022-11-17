package br.com.apc.chef.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * @author Archer
 */
public class BoardDto {
	
//	@Size(min = 4, max = 9)
	@NotNull
	private Integer size;

	private PartDto[][] urlImagesMatrix;
	
//	@NotNull
//	private PartDto pdto;
	
	/**
	 * @param size
	 * @param pdto
	 */
	public BoardDto( int size, PartDto[][] pdto) { //@Size(min=4, max=9)
		super();
		this.size = size;
		urlImagesMatrix = pdto;
	}

	public int getSize() {
		return size;
	}

}
