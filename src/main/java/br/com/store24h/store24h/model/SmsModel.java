/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.store24h.store24h.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author Archer
 */
@Entity
@Table(name = "sms_model", indexes = {
        @Index(name = "idx_chipnumber", columnList = "chipnumber")})
public class SmsModel implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false)
    private String msg;
    @Column(nullable = false)
    private String emissor;
    @Column(unique = true, nullable = false)
    private LocalDateTime date;
    @Column(nullable = false)
    private Integer sequencia;
    @Column(nullable = false)
    private String chipnumber;


    // TODO por campos de negócios como Boolean repassadoAoComprador

    public SmsModel(String msg, String emissor, LocalDateTime date, Integer sequencia) {
        this.msg = msg;
        this.emissor = emissor;
        this.date = date;
        this.sequencia = sequencia;
    }

    public SmsModel() {

    }

    public String getChipnumber() {
        return chipnumber;
    }

    public void setChipnumber(String chipnumber) {
        this.chipnumber = chipnumber;
    }

    public long getId() {
        return id;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getSequencia() {
        return sequencia;
    }

    public void setSequencia(Integer sequencia) {
        this.sequencia = sequencia;
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
