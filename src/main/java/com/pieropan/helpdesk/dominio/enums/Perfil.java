package com.pieropan.helpdesk.dominio.enums;

import java.util.Objects;

public enum Perfil {
    ADMIN(0, "ROLE_ADMIN"),
    CLIENTE(1, "ROLE_CLIENTE"),
    TECNICO(2, "ROLE_TECNICO");

    private Integer codigo;
    private String descricao;

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    Perfil(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static Perfil toEnum(Integer codigo) {
        if (Objects.isNull(codigo)) {
            return null;
        }

        for (Perfil perfil : Perfil.values()) {
            if (perfil.codigo.equals(codigo))
                return perfil;
        }

        throw new IllegalArgumentException("Perfil inválido");
    }
}