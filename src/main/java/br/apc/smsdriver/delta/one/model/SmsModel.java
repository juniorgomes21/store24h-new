/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.apc.smsdriver.delta.one.model;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Archer
 */
public class SmsModel {
    private String msg;
    private String emissor;
    private Date date;

    public SmsModel(String msg, String emissor, Date date) {
        this.msg = msg;
        this.emissor = emissor;
        this.date = date;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getEmissor() {
        return emissor;
    }

    public void setEmissor(String emissor) {
        this.emissor = emissor;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.msg);
        hash = 83 * hash + Objects.hashCode(this.emissor);
        hash = 83 * hash + Objects.hashCode(this.date);
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
        final SmsModel other = (SmsModel) obj;
        if (!Objects.equals(this.msg, other.msg)) {
            return false;
        }
        if (!Objects.equals(this.emissor, other.emissor)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }

}
