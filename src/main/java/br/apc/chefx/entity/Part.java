package br.apc.chefx.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "parts")
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String urlImage;
    private String description;
    private String sendBy;
    private String title;

    public Part(String urlImage, String description, String title) {

        this.urlImage = urlImage;
        this.description = description;
        this.title = title;

    }

    public long getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Part part = (Part) o;
        return id == part.id && urlImage.equals(part.urlImage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, urlImage);
    }
}
