package br.com.store24h.store24h.dto;

import br.com.store24h.store24h.model.User;

public class UserDTO {

    private String nome;
    private String email;
    private String cpf;
    private String apiKey;
    private String role;
    private int saldo;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.nome = user.getNome();
        this.email = user.getEmail();
        this.cpf = user.getCpf();
        this.apiKey = user.getApiKey();
        this.role = user.getPerfil();
//        this.saldo = user.getConta().getSaldo().intValue();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
}
