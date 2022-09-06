package br.com.store24h.store24h.dto;

public class ErrorCadastroDTO {
    private String msgError;

    public ErrorCadastroDTO(String msgError) {
        this.msgError = msgError;
    }

    public String getMsgError() {
        return msgError;
    }

    public void setMsgError(String msgError) {
        this.msgError = msgError;
    }
}
