package com.emergency.EmergencyTracker.enums;

import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

public enum PerfilEnum implements GrantedAuthority {

    ADMIN(0, "ADMIN");


    private int id;
    private String nome;

    private PerfilEnum(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public static PerfilEnum convert(String str) {
        for (PerfilEnum perfilEnum : PerfilEnum.values()) {
            if (perfilEnum.toString().equals(str)) {
                return perfilEnum;
            }
        }
        return null;
    }

    public static Collection<String> perfis() {
        Collection<String> perfis = new ArrayList<>();
        for (PerfilEnum perfilEnum : PerfilEnum.values()) {
            perfis.add(perfilEnum.name());
        }
        return perfis;
    }

    @Override
    public String getAuthority() {
        return "ROLE_" + this.nome;
    }
}