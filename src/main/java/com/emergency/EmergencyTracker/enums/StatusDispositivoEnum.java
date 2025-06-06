package com.emergency.EmergencyTracker.enums;

import lombok.Getter;

@Getter
public enum StatusDispositivoEnum {
    ATIVO,
    INATIVO,
    STANDY_BY;
}
   /* INATIVO(0),
    ATIVO(1);

    private final int valor;

    // Construtor do enum
    StatusEnum(int valor) {
        this.valor = valor;
    }

    public static StatusEnum fromValor(int valor) {
        for (StatusEnum status : values()) {
            if (status.getValor() == valor) {
                return status;
            }
        }
        throw new IllegalArgumentException("Valor inválido: " + valor);
    }
}*/
