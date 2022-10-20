package br.com.store24h.store24h.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ativacoes")
public class Activation {
    private long id;
    @OneToMany
    private Servico servico;
    private User user;
    private Msg msg;
    private String tempNumber; // uma hora fica inválido e enquanto válido, segue abaixo.
    private String siteNumber; // numero usado pelo site
    private int status = -1;
//     significa que o cadastro começou e um codigo já chegou do site que o usuário final está querendo se cadastrar
//            1 - Notify that SMS has been sent (optional)

//            store24 está pedindo uma confirmação do que já se tem e que veio do site
//            3 - Request another SMS

//            6 - Confirm SMS code and complete activation
//            8 - Cancel activation

    // cadastro não iniciado, aguardando chegar sms do site a se cadastrar = -1
    // recebido primeiro código numérico = 7
    // recebida msg após chegada do primeiro codigo numerico, possível confirmação de inscrição concluída = 11
    // recebida msg maior que 2, serviço envia varias infos = 13

    public Activation() {
    }

    public Activation(Servico servico, User user, String tempNumber, String siteNumber, int status) {
        this.servico = servico;
        this.user = user;
        this.tempNumber = tempNumber;
        this.siteNumber = siteNumber;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTempNumber() {
        return tempNumber;
    }

    public void setTempNumber(String tempNumber) {
        this.tempNumber = tempNumber;
    }

    public String getSiteNumber() {
        return siteNumber;
    }

    public void setSiteNumber(String siteNumber) {
        this.siteNumber = siteNumber;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
