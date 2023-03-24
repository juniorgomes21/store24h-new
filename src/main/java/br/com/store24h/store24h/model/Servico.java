package br.com.store24h.store24h.model;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import java.io.Serializable;

@Entity
@Table(name = "servicos", indexes = {
        @Index(name = "idx_alias", columnList = "alias")})
public class Servico implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String alias;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private BigDecimal defaultPrice;
    @Column(nullable = false)
    private boolean defaultMaxPrice;
    @Column(nullable = false)
    private BigDecimal maxPrice;
    @ElementCollection(fetch = FetchType.EAGER)
    private Map<String, Integer> priceMap;
    private boolean random;
    private int quantityForMaxPrice = 30;
    private boolean activity = true;
    @PositiveOrZero
    private int totalQuantity;
    private boolean canAuction;
    private ArrayList<Object> auctionMap = new ArrayList<>();
    private boolean work;

    public Servico() {
    }

    public Servico(String name, String alias, BigDecimal price) {
        this.name = name;
        this.alias = alias;
        this.price = price;
        this.defaultPrice = price;
        this.maxPrice = price;
        this.defaultMaxPrice = true;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Map<String, Integer> getPriceMap() {
        return priceMap;
    }

    public void setPriceMap(Map<String, Integer> priceMap) {
        this.priceMap = priceMap;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public BigDecimal getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(BigDecimal defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public boolean isDefaultMaxPrice() {
        return defaultMaxPrice;
    }

    public void setDefaultMaxPrice(boolean defaultMaxPrice) {
        this.defaultMaxPrice = defaultMaxPrice;
    }

    public boolean isRandom() {
        return random;
    }

    public boolean isActivity() {
        return activity;
    }

    public void setActivity(boolean activity) {
        this.activity = activity;
    }

    public void setRandom(boolean random) {
        this.random = random;
    }

    public int getQuantityForMaxPrice() {
        return quantityForMaxPrice;
    }

    public void setQuantityForMaxPrice(int quantityForMaxPrice) {
        this.quantityForMaxPrice = quantityForMaxPrice;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public boolean isCanAuction() {
        return canAuction;
    }

    public void setCanAuction(boolean canAuction) {
        this.canAuction = canAuction;
    }

    public ArrayList<Object> getAuctionMap() {
        return auctionMap;
    }

    public void setAuctionMap(ArrayList<Object> auctionMap) {
        this.auctionMap = auctionMap;
    }

    public boolean isWork() {
        return work;
    }

    public void setWork(boolean work) {
        this.work = work;
    }

}
