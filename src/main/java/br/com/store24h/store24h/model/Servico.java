package br.com.store24h.store24h.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "servicos")
public class Servico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String codeServico;
    private BigDecimal price;

    public Servico() {
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

    public String getCodeServico() {
        return codeServico;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setCodeServico(String codeServico) {
        this.codeServico = codeServico;
    }
}
