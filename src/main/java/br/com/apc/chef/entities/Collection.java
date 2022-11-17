package br.com.apc.chef.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "collections")
public class Collection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @OneToOne
    private Board board;
    
    @JsonIgnore
    @OneToMany(orphanRemoval=true)
    private final List<CollectionUserInfo> collectionUserInfos = new ArrayList<>();
        
    /**
	 * @param board
	 */
	public Collection(@NotBlank Board board) {
		super();
		this.board = board;
	}

    public Collection() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Board getBoard() {
        return board;
    }

    public Board getBoards() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<CollectionUserInfo> getCollectionUserInfos() {
        return collectionUserInfos;
    }

    public void addCollectionUserInfo(CollectionUserInfo collectionUserInfo) {
		collectionUserInfos.add(collectionUserInfo);
	}
}
