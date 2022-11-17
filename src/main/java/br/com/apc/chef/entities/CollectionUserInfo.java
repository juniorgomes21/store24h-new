package br.com.apc.chef.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Entity
//@Table(name = "collection_user_infos")
public class CollectionUserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @OneToOne
    private User user;

    boolean[][] matrix;

////    @NotBlank
////    @ManyToOne 
//    private Collection collection;

    public CollectionUserInfo() {
		// TODO Auto-generated constructor stub
	}
    
    public CollectionUserInfo(User user, Collection collection) {
        this.user = user;
//        this.collection = collection;
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

//    public Collection getCollection() {
//        return collection;
//    }
//
//    public void setCollection(Collection collection) {
//        this.collection = collection;
//    }

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
