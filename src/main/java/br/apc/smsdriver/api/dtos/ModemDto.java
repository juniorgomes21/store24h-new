package br.apc.smsdriver.api.dtos;

import br.apc.smsdriver.entities.ChipModel;

import java.util.Objects;

public class ModemDto {

    private String fabricante;
    private String modelo;
    private String modOpradora;
    private long imei;
    private String gcap;
    private String portName;
    private ChipDto chip;

    public ModemDto(String fabricante, String modelo, String modOpradora, long imei, String gcap, String portName, ChipDto chip) {
        this.fabricante = fabricante;
        this.modelo = modelo;
        this.modOpradora = modOpradora;
        this.imei = imei;
        this.gcap = gcap;
        this.portName = portName;
        this.chip = chip;
    }

    public String getPortName() {
        return portName;
    }

    public String getFabricante() {
        return fabricante;
    }

    public String getModelo() {
        return modelo;
    }

    public String getModOpradora() {
        return modOpradora;
    }

    public long getImei() {
        return imei;
    }

    public String getGcap() {
        return gcap;
    }

    public ChipDto getChip() {
        return chip;
    }

    @Override
    public String toString() {
        return "ModemDto{" +
                "fabricante='" + fabricante + '\'' +
                ", modelo='" + modelo + '\'' +
                ", modOpradora='" + modOpradora + '\'' +
                ", imei=" + imei +
                ", gcap='" + gcap + '\'' +
                ", portName='" + portName + '\'' +
                ", chip=" + chip +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModemDto modemDto = (ModemDto) o;
        return imei == modemDto.imei && modelo.equals(modemDto.modelo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelo, imei);
    }
}
