package br.com.store24h.store24h.services.core;

public enum ActivationStatus {
    SOLICITADA(2),
    AGUARDANDO_MENSAGENS(3),
    FINALIZADA(5),
    CANCELADA(7);

    ActivationStatus(int i) {
    }
}
