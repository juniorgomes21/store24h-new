package br.com.store24h.store24h.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ChipNumberControl {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String chipNumber;
    @OneToMany
    private List<Servico> servicos = new ArrayList<>();

    public ChipNumberControl() {
    }

    public Long getId() {
        return id;
    }

    public String getChipNumber() {
        return chipNumber;
    }

    public void setChipNumber(String chipNumber) {
        this.chipNumber = chipNumber;
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }
}
