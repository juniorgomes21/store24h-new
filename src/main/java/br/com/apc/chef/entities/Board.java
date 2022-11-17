package br.com.apc.chef.entities;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

@Entity
public abstract class Board {
	private final int SIZE = getRow()*getColumn();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
        
    @Size(min = 4, max = 9)
    @OneToMany
        private List<Part> part;

    private String image;
    
    private final String[][] urlImagesMatrix =  new String[getSize()][getSize()];
    
    public int getSize() {
    	return SIZE;
    }; //4,9
    
	abstract int getColumn();
    
    abstract  int getRow();

    public String getImage() {
        return image;
    }

    public void setImage(String getImage) {
        this.image = getImage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return id == board.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

	public String[][] getUrlImagesMatrix() {
		return urlImagesMatrix;
	}

}
