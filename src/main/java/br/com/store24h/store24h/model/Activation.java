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
    private String serviceName;
    @NotBlank
    private String aliasService;
    private String apiKey;
    private String serviceNumber;
    private BigDecimal servicePrice;
    private String chipNumber;
    private ActivationStatus statusBuz = ActivationStatus.SOLICITADA;
    @ElementCollection
    @CollectionTable(name = "sms_string_models", joinColumns = @JoinColumn(name = "activation_id"))
    @Column(name = "sms_string_models")
    @Fetch(FetchMode.JOIN)
    private final List<String> smsStringModels = new ArrayList<>();
    private Integer neededSmsToFinalize = 1;
    private LocalDateTime initialTime = LocalDateTime.now(ZoneId.of(TimeZone.BR.getZone()));
    private LocalDateTime endTime;
    private int status = -1;

    public Activation() {
    }
    public Activation(Servico service, String chipNumber, String apiKey) {
        this.aliasService = service.getAlias();
        this.serviceName = service.getName();
        this.servicePrice = service.getPrice();
        this.chipNumber = chipNumber;
        this.apiKey = apiKey;
    }
    public Activation(String serviceName, String chipNumber) {
        this.serviceName = serviceName;
        this.chipNumber = chipNumber;
    }

    public List<String> getSmsStringModels() {
        return smsStringModels;
    }

    public boolean reserveNumber() {
        boolean resp = false;
        this.setInitialTime(LocalDateTime.now(ZoneId.of(TimeZone.BR.getZone())));
        resp = true;
        return resp;
    };


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
