package br.com.store24h.store24h.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class PaisOperadoras {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String pais;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> operadoras = new ArrayList<>();

    public PaisOperadoras() {
    }

    public PaisOperadoras(String nome, String pais, List<String> operadoras) {
        this.nome = nome;
        this.pais = pais;
        this.operadoras = operadoras;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public List<String> getOperadoras() {
        return operadoras;
    }

    public void setOperadoras(List<String> operadoras) {
        this.operadoras = operadoras;
    }
}
