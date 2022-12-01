package br.com.store24h.store24h.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class ComprasCredito {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime localDateTime;
    private BigDecimal valorComprado;

    public ComprasCredito() {
    }

    public ComprasCredito(LocalDateTime localDateTime, BigDecimal valorComprado) {
        this.localDateTime = localDateTime;
        this.valorComprado = valorComprado;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public BigDecimal getValorComprado() {
        return valorComprado;
    }

    public void setValorComprado(BigDecimal valorComprado) {
        this.valorComprado = valorComprado;
    }
}
