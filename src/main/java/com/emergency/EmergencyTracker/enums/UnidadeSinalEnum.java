package com.emergency.EmergencyTracker.enums;

public enum UnidadeSinalEnum {
    MILIMETRO(0,"mm"),
    METROS_POR_SEGUNDO(1, "m/s"),
    QUILOMETRO(2, "Km"),
    PERCENTUAL(3, "%");

    private int id;
    private String unidade;
    public static UnidadeSinalEnum convert(String str) {
        for (UnidadeSinalEnum aut : UnidadeSinalEnum.values()) {
            if (aut.toString().equalsIgnoreCase(str)) {
                return aut;
            }
        }
        return null;
    }
    public int getId() {
        return id;
    }

    public String getUnidade() {
        return unidade;
    }

    UnidadeSinalEnum(int id, String unidade) {
        this.id = id;
        this.unidade = unidade;
    }
}

