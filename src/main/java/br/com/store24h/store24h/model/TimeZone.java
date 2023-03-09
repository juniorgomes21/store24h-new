package br.com.store24h.store24h.model;

public enum TimeZone {
    BR("America/Sao_Paulo");

    private String zone;

    TimeZone(String zone) {
        this.zone = zone;
    }

    public String getZone() {
        return zone;
    }
}
