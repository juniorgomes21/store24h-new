package br.com.store24h.store24h.dto;

import br.com.store24h.store24h.model.Activation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class HistoryBuysDTO {
    private Long idActivation;
    private LocalDateTime localDateTime;
    private String aliasService;
    private String number;
    private String sms = "";
    private BigDecimal cost;
    private int status = 1;
    private Long idUser;

    public HistoryBuysDTO() {
    }

    public HistoryBuysDTO(Activation activation) {
        this.idActivation = activation.getId();
        this.localDateTime = activation.getInitialTime();
        this.aliasService = activation.getAliasService();
        this.number = activation.getChipNumber();
        this.sms = activation.getSmsStringModels().get(0);
        this.cost = activation.getServicePrice();
        this.status = activation.getStatus();
        this.idUser = -1L;
    }

    public Long getIdActivation() {
        return idActivation;
    }

    public void setIdActivation(Long idActivation) {
        this.idActivation = idActivation;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getAliasService() {
        return aliasService;
    }

    public void setAliasService(String aliasService) {
        this.aliasService = aliasService;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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
