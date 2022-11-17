package br.apc.chefx.entity;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "collections")
public class Collection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Board boards;

    @OneToMany(mappedBy = "collection")
    private ArrayList<CollectionUserInfo> collectionUserInfos;

    public Collection(Board boards) {
        this.boards = boards;
    }

    public Board getBoards() {
        return boards;
    }

    public void setBoards(Board boards) {
        this.boards = boards;
    }

    public ArrayList<CollectionUserInfo> getCollectionUserInfos() {
        return collectionUserInfos;
    }


}
