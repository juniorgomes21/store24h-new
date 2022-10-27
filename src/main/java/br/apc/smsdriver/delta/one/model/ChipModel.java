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
public class ChipModel {
    private String operadora;
    private String number;
    private boolean isFree = true;

    public ChipModel(String number) {
        this.number = number;
    }

    public String getOperadora() {
        return operadora;
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
        return "ChipModel{" + "operadora=" + operadora + ", number=" + number + ", isFree=" + isFree + '}';
    }

    public boolean isIsFree() {
        return isFree;
    }

    public void setIsFree(boolean isFree) {
        this.isFree = isFree;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.number);
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
        final ChipModel other = (ChipModel) obj;
        if (!Objects.equals(this.number, other.number)) {
            return false;
        }
        return true;
    }


}
