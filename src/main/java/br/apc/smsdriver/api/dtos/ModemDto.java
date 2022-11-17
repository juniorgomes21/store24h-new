package br.apc.smsdriver.api.dtos;

import br.apc.smsdriver.entities.ChipModel;

public class ModemDto {

    private String fabricante;
    private String modelo;
    private String modOpradora;
    private long imei;
    private String gcap;
    private String portName;
    private ChipModel chip;

    public ModemDto(String fabricante, String modelo, String modOpradora, long imei, String gcap, String portName, ChipModel chip) {
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

    public ChipModel getChip() {
        return chip;
    }
}
