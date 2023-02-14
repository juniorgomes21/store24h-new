package br.com.store24h.store24h.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ChipNumberControl {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private String chipNumber;
    @ElementCollection
    @CollectionTable(name = "chip_number_control_alias_service", joinColumns = @JoinColumn(name = "chip_number_control_id"))
    @Column(name = "alias_service")
    private List<String> aliasService = new ArrayList<>();

    public ChipNumberControl() {
    }

    public Long getId() {
        return id;
    }

    public String getChipNumber() {
        return chipNumber;
    }

    public void setChipNumber(String chipNumber) {
        this.chipNumber = chipNumber;
    }

    public List<String> getAliasService() {
        return aliasService;
    }

    public void setAliasService(List<String> aliasService) {
        this.aliasService = aliasService;
    }
}
