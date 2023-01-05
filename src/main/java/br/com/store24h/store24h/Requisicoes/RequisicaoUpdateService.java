package br.com.store24h.store24h.Requisicoes;

import java.math.BigDecimal;

public class RequisicaoUpdateService {
    private BigDecimal price;

    public RequisicaoUpdateService() {
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
