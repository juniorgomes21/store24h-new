package br.apc.smsdriver.api.dtos;

import java.util.Date;

public class SmsDto {

    private String msg;
    private String emissor;
    private Date date;
    private int sequencial;

    public SmsDto(String msg, String emissor, Date date, int sequencial) {
        this.msg = msg;
        this.emissor = emissor;
        this.date = date;
        this.sequencial = sequencial;
    }

    @Override
    public String toString() {
        return "SmsDto{" +
//                "msg='" + msg + '\'' +
                ", sequencial=" + sequencial +
                '}';
    }

    public SmsDto(String msg, String emissor, Date date) {
        this.msg = msg;
        this.emissor = emissor;
        this.date = date;
    }

    public String getMsg() {
        return msg;
    }

    public String getEmissor() {
        return emissor;
    }

    public int getSequencial() {
        return sequencial;
    }

    public void setSequencial(int sequencial) {
        this.sequencial = sequencial;
    }

    public Date getDate() {
        return date;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
