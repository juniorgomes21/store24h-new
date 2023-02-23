package br.com.store24h.store24h.Requisicoes.services;

import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class ParamEditPriceSevice {

    @Positive
    @Max(1000)
    private BigDecimal newPrice;

    public ParamEditPriceSevice() {
    }

    public BigDecimal getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(BigDecimal newPrice) {
        this.newPrice = newPrice;
    }
}
