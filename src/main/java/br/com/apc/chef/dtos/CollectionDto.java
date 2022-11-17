package br.com.apc.chef.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Archer
 *
 */
public class CollectionDto {
	
	private Long idCollection;

	@NotBlank
	private String title;	

	@NotBlank
	private String urlImage;

	@NotBlank
	private String urlVideo;

	@NotNull
	private BoardDto board;

	public CollectionDto(String title, String urlImage, String urlVideo, BoardDto board) {
		this.title = title;
		this.urlImage = urlImage;
		this.urlVideo = urlVideo;
		this.board = board;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrlImage() {
		return urlImage;
	}
	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}
	public String getUrlVideo() {
		return urlVideo;
	}
	public void setUrlVideo(String urlVideo) {
		this.urlVideo = urlVideo;
	}
	public Long getIdCollection() {
		return idCollection;
	}
	public void setIdCollection(Long idCollection) {
		this.idCollection = idCollection;
	}
	public BoardDto getBoard() {
		return board;
	}
	public void setBoard(BoardDto board) {
		this.board = board;
	}
	
}
