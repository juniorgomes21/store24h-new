package br.com.apc.chef.dtos;

import javax.validation.constraints.NotBlank;

/**
 * @author Archer
 *
 */
public class PartDto {

	private long id;
	
	@NotBlank
	private String urlImage;
	
	@NotBlank
	private String description;
    
	@NotBlank
	private String sendBy;
    
	@NotBlank
	private String title;

	/**
	 * @param urlImage
	 * @param description
	 * @param sendBy
	 * @param title
	 */
	public PartDto(String urlImage, String description, String sendBy, String title) {
		super();
		this.urlImage = urlImage;
		this.description = description;
		this.sendBy = sendBy;
		this.title = title;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSendBy() {
		return sendBy;
	}

	public void setSendBy(String sendBy) {
		this.sendBy = sendBy;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
}
