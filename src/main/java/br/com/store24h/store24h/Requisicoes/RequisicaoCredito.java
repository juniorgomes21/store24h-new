package br.com.store24h.store24h.Requisicoes;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

public class RequisicaoCredito {
    private BigDecimal creditoParaAdicionar;

    public BigDecimal getCreditoParaAdicionar() {
        return creditoParaAdicionar;
    }

    public void setCreditoParaAdicionar(BigDecimal creditoParaAdicionar) {
        this.creditoParaAdicionar = creditoParaAdicionar;
    }

    public BigDecimal getValue() {

        return creditoParaAdicionar;
    }

}
