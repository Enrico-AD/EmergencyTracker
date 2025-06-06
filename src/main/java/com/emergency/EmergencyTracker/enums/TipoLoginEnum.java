package com.emergency.EmergencyTracker.enums;

public enum TipoLoginEnum {
    ADMIN("Administrador"),
    USER("Usuário");

    private final String descricao;

    TipoLoginEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}