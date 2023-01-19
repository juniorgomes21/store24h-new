package br.com.store24h.store24h.dto;

import br.com.store24h.store24h.model.SmsModel;

import java.util.List;

public class SmsDTO {
    private String aliasService;
    private String numberActivation;
    private List<String> smsList;
    public SmsDTO() {
    }

    public String getAliasService() {
        return aliasService;
    }

    public void setAliasService(String aliasService) {
        this.aliasService = aliasService;
    }

    public String getNumberActivation() {
        return numberActivation;
    }

    public void setNumberActivation(String numberActivation) {
        this.numberActivation = numberActivation;
    }

    public List<String> getSmsList() {
        return smsList;
    }

    public void setSmsList(List<String> smsList) {
        this.smsList = smsList;
    }
}
