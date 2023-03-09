package br.com.store24h.store24h.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
public class CompraServiso {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime localDateTime;
    private String chipNumber;
    private String servico;

    public CompraServiso() {
    }

    public CompraServiso(String servico, String chipNumber) {
        this.servico = servico;
        this.chipNumber = chipNumber;
        this.localDateTime = LocalDateTime.now(ZoneId.of(TimeZone.BR.getZone()));
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

    public String getChipNumber() {
        return chipNumber;
    }

    public void setChipNumber(String chipNumber) {
        this.chipNumber = chipNumber;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }
}
