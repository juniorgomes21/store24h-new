package br.com.store24h.store24h.model;

public enum Role {
    USER("USER"),
    ADMINISTRADOR("ADMINISTRADOR"),
    DESCONHECIDO("DESCONHECIDO");

    private String nome;

    Role(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
