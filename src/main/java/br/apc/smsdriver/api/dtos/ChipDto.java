package br.apc.smsdriver.api.dtos;

import java.io.Serializable;
import java.util.Objects;

public class ChipDto implements Serializable {
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

    @Override
    public String toString() {
        return "ChipDto{" +
                "operadora='" + operadora + '\'' +
                ", number='" + number + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChipDto chipDto = (ChipDto) o;
        return operadora.equals(chipDto.operadora) && number.equals(chipDto.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operadora, number);
    }
}
