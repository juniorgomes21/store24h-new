package br.apc.smsdriver.api.dtos;

public class ChipDto {
    private String operadora;
    private String number;

    public String getOperadora() {
        return operadora;
    }

    public ChipDto(String operadora, String number) {
        this.operadora = operadora;
        this.number = number;
    }

    public void setOperadora(String operadora) {
        this.operadora = operadora;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
