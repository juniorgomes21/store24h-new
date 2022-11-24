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
    private String fabricante;
    private String modelo;
    private String modOpradora;
    private String portaEmUso;
    private ChipModel chip;

    public ModemModel(String portaEmUso) {
        this.portaEmUso = portaEmUso;
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

    public String getPortaEmUso() {
        return portaEmUso;
    }

    public void setPortaEmUso(String portaEmUso) {
        this.portaEmUso = portaEmUso;
    }

    public ChipModel getChip() {
        return chip;
    }

    public void setChip(ChipModel chip) {
        this.chip = chip;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.portaEmUso);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ModemModel other = (ModemModel) obj;
        if (!Objects.equals(this.portaEmUso, other.portaEmUso)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ModemModel{" + "portaEmUso=" + portaEmUso + ", chip=" + chip + '}';
    }


}
