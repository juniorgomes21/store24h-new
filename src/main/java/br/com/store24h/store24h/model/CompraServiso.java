package br.com.store24h.store24h.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Table(name = "registro_de_compras")
public class CompraServiso {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idActivation;
    private LocalDateTime localDateTime;
    private String aliasService;
    private String number;
    private String sms = "";
    private BigDecimal cost;
    private int status = 1;
    private Long idUser;

    public CompraServiso() {
    }

    public CompraServiso(Long idActivation, String aliasService, String number, BigDecimal cost, Long idUser) {
        this.idActivation = idActivation;
        this.localDateTime = LocalDateTime.now(ZoneId.of(TimeZone.BR.getZone()));
        this.aliasService = aliasService;
        this.number = number;
        this.cost = cost;
        this.idUser = idUser;
    }

    public Long getId() {
        return id;
    }

    public String getAliasService() {
        return aliasService;
    }

    public void setAliasService(String aliasService) {
        this.aliasService = aliasService;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getIdActivation() {
        return idActivation;
    }

    public void setIdActivation(Long idActivation) {
        this.idActivation = idActivation;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }
}
