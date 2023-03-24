package br.com.store24h.store24h.model;

import br.com.store24h.store24h.services.StatusService;
import br.com.store24h.store24h.services.core.ActivationStatus;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * De forma geral deve ser simples e sem muita validação, apenas se há saldo ou não na key_api
 */
@Entity
public class Activation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    @Column(unique = true)
    private String serviceName;
    @NotBlank
    private String aliasService;
    //    @NotBlank
    private String apiKey;
    private String serviceNumber;
    private BigDecimal servicePrice;
    private String chipNumber; // uma hora fica inválido e enquanto válido, segue abaixo. É o numero usado para esta ativação
    //    private String siteNumber; // numero usado pelo site onde se tenta realizar o serviço
//    SOLICITADA(2),
//    AGUARDANDO_MENSAGENS(3),
//    FINALIZADA(5),
//    CANCELADA(7);
    private ActivationStatus statusBuz = ActivationStatus.SOLICITADA; // SOLICITADA e com ID fornecida | NUMERO_FORNECIDO |
    // AGUARDANDO_MENSAGENS | FINALIZADA | CANCELADA
    @ElementCollection
    @CollectionTable(name = "sms_string_models", joinColumns = @JoinColumn(name = "activation_id"))
    @Column(name = "sms_string_models")
    @Fetch(FetchMode.JOIN)
    private final List<String> smsStringModels = new ArrayList<>();
    private Integer neededSmsToFinalize = 1;
    private LocalDateTime initialTime = LocalDateTime.now(ZoneId.of(TimeZone.BR.getZone()));
    private LocalDateTime endTime;

//            Significa que o cadastro começou e um codigo já chegou do site que o usuário final
//            está querendo se cadastrar
//            1 - Notify that SMS has been sent (optional)
//
//            Neste ponto o cliente final já tem que ter solicitado um novo número.
//            store24 está pedindo uma confirmação do que já se tem e que veio do site
//            neste caso, para este serviço, ainda pode ser que não tenha todos os sms, buscar de novo
//            até este momento o chip está travado então, assim tudo que se faz é ver se há novas menssagens
//            3 - Request another SMS
//
//            Uma vez que chegou um novo sms no chip e este foi enviado para o site parceiro
//            considera-se que o serviço está concluido e já é possível liberar i chip para novas "ativações"
//            6 - Confirm SMS code and complete activation
//
//            Neste caso foi indicado que é para marcar como livre o tipo de serviço para este chip, independente
//            do que tenha acontecido antes, simplesmente o chip deve estar livre para novas ativações e possíveis
//            antigas devem ser marcadas como canceladas. Isto torna necessário um campo para registrar tal.
//            8 - Cancel activation

    // cadastro não iniciado, aguardando chegar sms do site a se cadastrar = -1
    // recebido primeiro código numérico = 7
    // recebida msg após chegada do primeiro codigo numerico, possível confirmação de inscrição concluída = 11
    // recebida msg maior que 2, serviço envia varias infos = 13
    private int status = -1;

    public Activation() {
    }
    public Activation(Servico service, String chipNumber, String apiKey) {
        this.aliasService = service.getAlias();
        this.serviceName = service.getName();
        this.servicePrice = service.getPrice();
        this.chipNumber = chipNumber;
        this.apiKey = apiKey;
//        this.siteNumber = siteNumber;
        // this.status = status;
    }
    public Activation(String serviceName, String chipNumber) {
        this.serviceName = serviceName;
        this.chipNumber = chipNumber;
//        this.siteNumber = siteNumber;
        // this.status = status;
    }

    public List<String> getSmsStringModels() {
        return smsStringModels;
    }

    public boolean reserveNumber() {
        boolean resp = false;
        this.setInitialTime(LocalDateTime.now(ZoneId.of(TimeZone.BR.getZone())));
//        TODO
//        tempNumber
//        setTempNumber();
        resp = true;
        return resp;
    };

    /**
     * Seta valores internos apropriadamente
     * @return
     * @TODO Ver depois
     */
//    public boolean finalizeService() {
//        throw Un
//        boolean resp = false;
//        // é importante tratar este estado, pois ele influencia na contabilidade
//        if (smsModels.size() > 0) {
////                    throw new RuntimeException(Activation.class.getName() + " : Para finalizar deve existir algum sms");
//            resp = true;
//            this.setEndTime(LocalDateTime.now(ZoneId.of(TimeZone.BR.getZone())));
//            this.statusBuz = ActivationStatus.FINALIZADA;
//        }
//        return resp;
//    };

    /**
     * Seta valores internos apropriadamente
     * @return
     */
    public boolean cancelService() {
        statusBuz = ActivationStatus.CANCELADA;
        return true;
    };

    @Override
    public String toString() {
        return "Activation{" +
                "serviceName='" + serviceName + '\'' +
                ", serviceNumber='" + serviceNumber + '\'' +
                ", chipNumber='" + chipNumber + '\'' +
                ", statusBuz=" + statusBuz +
                ", smsStringModels=" + smsStringModels +
                ", neededSmsToFinalize=" + neededSmsToFinalize +
                ", initialTime=" + initialTime +
                ", endTime=" + endTime +
                ", status=" + status +
                '}';
    }

    public String getServiceNumber() {
        return serviceNumber;
    }

    public void setServiceNumber(String serviceNumber) {
        this.serviceNumber = serviceNumber;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setStatusBuz(ActivationStatus statusBuz) {
        this.statusBuz = statusBuz;
    }

    public long getId() {
        return id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getAliasService() {
        return aliasService;
    }

    public void setAliasService(String aliasService) {
        this.aliasService = aliasService;
    }

    public String getApiKey() {
        return apiKey;
    }

    public BigDecimal getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(BigDecimal servicePrice) {
        this.servicePrice = servicePrice;
    }

    public String getChipNumber() {
        return chipNumber;
    }

    public ActivationStatus getStatusBuz() {
        return statusBuz;
    }

    public Integer getNeededSmsToFinalize() {
        return neededSmsToFinalize;
    }

    public void setChipNumber(String chipNumber) {
        this.chipNumber = chipNumber;
    }

    public void setNeededSmsToFinalize(Integer neededSmsToFinalize) {
        this.neededSmsToFinalize = neededSmsToFinalize;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public LocalDateTime getInitialTime() {
        return initialTime;
    }

    public void setInitialTime(LocalDateTime initialTime) {
        this.initialTime = initialTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
