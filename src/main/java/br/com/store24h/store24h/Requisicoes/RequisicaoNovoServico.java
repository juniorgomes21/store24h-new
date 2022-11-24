package br.com.store24h.store24h.Requisicoes;

import br.com.store24h.store24h.model.Servico;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

@Service
public class RequisicaoNovoServico {
    private String SENHA;
    private String name;
    private String alias;
    private BigDecimal price;
    private BigDecimal defaultPrice;
    private boolean defaultMaxPrice;
    private BigDecimal maxPrice;
    private Map<String, Integer> priceMap;

    public RequisicaoNovoServico() {
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

    public boolean isDefaultMaxPrice() {
        return defaultMaxPrice;
    }

    public void setDefaultMaxPrice(boolean defaultMaxPrice) {
        this.defaultMaxPrice = defaultMaxPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Map<String, Integer> getPriceMap() {
        return priceMap;
    }

    public void setPriceMap(Map<String, Integer> priceMap) {
        this.priceMap = priceMap;
    }



    public Servico toServico() {
        Servico serv = new Servico();

        serv.setName(name);
        serv.setAlias(alias);
        serv.setPrice(price);
        serv.setPriceMap(priceMap);
        serv.setDefaultPrice(defaultPrice);
        serv.setDefaultMaxPrice(defaultMaxPrice);
        serv.setMaxPrice(maxPrice);

        return serv;
    }
}
