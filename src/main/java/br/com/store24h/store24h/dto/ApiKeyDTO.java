package br.com.store24h.store24h.dto;

public class ApiKeyDTO {
    private String apiKey;

    public ApiKeyDTO(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
