/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.apc.smsdriver.delta.one.model;

import java.util.Objects;

/**
 *
 * @author Archer
 */
public class ModemModel {
    private final String portName;
    private long id;
    private String fabricante;
    private String modelo;
    private String modOpradora;
    private long imei;
    private String gcap;
    private ChipModel chip;

    public ModemModel(String portName, String model, String manufacturer, long imei, String revision, String gcap) {
        this.portName = portName;
        this.modelo = model;
        this.fabricante = manufacturer;
        this.modOpradora = revision;
        this.gcap = gcap;
        this.imei = imei;
    }

    public String getPortName() {
        return portName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getModOpradora() {
        return modOpradora;
    }

    public void setModOpradora(String modOpradora) {
        this.modOpradora = modOpradora;
    }

    public long getImei() {
        return imei;
    }

    public void setImei(long imei) {
        this.imei = imei;
    }

    public String getGcap() {
        return gcap;
    }

    public void setGcap(String gcap) {
        this.gcap = gcap;
    }

    public ChipModel getChip() {
        return chip;
    }

    public void setChip(ChipModel chip) {
        this.chip = chip;
    }

    @Override
    public String toString() {
        return "ModemModel{" +
                "portName='" + portName + '\'' +
                ", id=" + id +
                ", fabricante='" + fabricante + '\'' +
                ", modelo='" + modelo + '\'' +
                ", modOpradora='" + modOpradora + '\'' +
                ", imei='" + imei + '\'' +
                ", gcap='" + gcap + '\'' +
                ", chip=" + chip +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModemModel that = (ModemModel) o;
        return id == that.id && imei == that.imei && portName.equals(that.portName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(portName, id, imei);
    }
}
