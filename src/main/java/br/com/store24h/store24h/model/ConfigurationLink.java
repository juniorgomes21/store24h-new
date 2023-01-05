package br.com.store24h.store24h.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ConfigurationLink {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String apiLink;

    public ConfigurationLink() {
    }

    public ConfigurationLink(String apiLink) {
        this.apiLink = apiLink;
    }

    public String getApiLink() {
        return apiLink;
    }

    public void setApiLink(String apiLink) {
        this.apiLink = apiLink;
    }
}
