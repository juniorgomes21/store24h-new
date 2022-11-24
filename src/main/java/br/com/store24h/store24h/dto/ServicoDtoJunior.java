package br.com.store24h.store24h.dto;

import br.com.store24h.store24h.model.Servico;

import javax.persistence.Column;
import java.math.BigDecimal;

public class ServicoDtoJunior {
    private Long id;
    private String name;
    private String alias;
    private BigDecimal price;
    private BigDecimal defaultPrice;

    public ServicoDtoJunior(Servico servico) {
        this.id = servico.getId();
        this.name = servico.getName();
        this.alias = servico.getAlias();
        this.price = servico.getPrice();
        this.defaultPrice = servico.getDefaultPrice();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(BigDecimal defaultPrice) {
        this.defaultPrice = defaultPrice;
    }
}
