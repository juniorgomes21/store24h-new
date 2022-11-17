package br.apc.chefx.entity;

import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

public abstract class Board {
    private long id;
    private String getImage;
    @Size(min = 4, max = 9)
    private List<Part> part;
    abstract int getSize(); //4,9
    abstract int[][] matrix();
    abstract int getColumn();
    abstract  int getRow();

    public String getGetImage() {
        return getImage;
    }

    public void setGetImage(String getImage) {
        this.getImage = getImage;
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

}
