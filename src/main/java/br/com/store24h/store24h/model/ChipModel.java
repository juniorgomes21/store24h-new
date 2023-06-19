/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.store24h.store24h.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Archer
 */
@Entity
@Table(name = "chip_model", indexes = {
        @Index(name = "idx_alugado", columnList = "alugado"),
        @Index(name = "idx_ativo", columnList = "ativo")})
public class ChipModel implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String operadora;
    @Column(unique = true, nullable = false)
    private String number;
    private Boolean ativo = false;
    private Boolean alugado = false;
    private Boolean checked = false;
    private int status = 0;


    public ChipModel(String number) {
        this.number = number;
    }

    public ChipModel() {

    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Boolean getAlugado() {
        return alugado;
    }

    public void setAlugado(Boolean alugado) {
        this.alugado = alugado;
    }

    public long getId() {
        return id;
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

    public int getStatus() {
        return status;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "ChipModel{" + "operadora=" + operadora + ", number=" + number + '}';
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
