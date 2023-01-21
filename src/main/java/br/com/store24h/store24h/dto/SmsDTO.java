package br.com.store24h.store24h.dto;

import br.com.store24h.store24h.model.SmsModel;

import java.util.ArrayList;
import java.util.List;

public class SmsDTO {
    private String nameService;
    private String aliasService;
    private String numberActivation;
    private List<String> smsList = new ArrayList<>();

    public SmsDTO() {
    }

    public String getNameService() {
        return nameService;
    }

    public void setNameService(String nameService) {
        this.nameService = nameService;
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
