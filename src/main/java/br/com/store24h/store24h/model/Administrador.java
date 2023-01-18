package br.com.store24h.store24h.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "administradores")
public class Administrador extends User {

    private String operator;
    private String country;

    public Administrador() {
    }

    public Administrador(String operator, String country) {
        this.operator = operator;
        this.country = country;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
