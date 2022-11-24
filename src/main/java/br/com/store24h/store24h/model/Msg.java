package br.com.store24h.store24h.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "mensagens")
public class Msg implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int chipNumber;
    private LocalDateTime data;
    private String conteudo;
    private int numTelefoneServico;

    public Msg() {
    }

    public int getChipNumber() {
        return chipNumber;
    }

    public void setChipNumber(int chipNumber) {
        this.chipNumber = chipNumber;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public int getNumTelefoneServico() {
        return numTelefoneServico;
    }

    public void setNumTelefoneServico(int numTelefoneServico) {
        this.numTelefoneServico = numTelefoneServico;
    }
}
