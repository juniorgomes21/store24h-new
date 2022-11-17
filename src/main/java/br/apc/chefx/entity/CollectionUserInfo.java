package br.apc.chefx.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "collection_user_infos")
public class CollectionUserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private User user;

    @ManyToOne
    private Collection collection;

    boolean[][] matrix = new boolean[3][3];

    public CollectionUserInfo(User user, Collection collection) {
        this.user = user;
        this.collection = collection;
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public boolean[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(boolean[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CollectionUserInfo that = (CollectionUserInfo) o;
        return id == that.id && user.equals(that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user);
    }
}
