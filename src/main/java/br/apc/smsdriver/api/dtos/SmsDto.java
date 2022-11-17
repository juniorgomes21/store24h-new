package br.apc.smsdriver.api.dtos;

import java.util.Date;

public class SmsDto {

    private String msg;
    private String emissor;
    private Date date;

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

    public Date getDate() {
        return date;
    }
}
