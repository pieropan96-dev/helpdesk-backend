package com.pieropan.helpdesk.dominio.enums;

import java.util.Objects;

public enum PRIORIDADE {
    BAIXA(0, "BAIXA"),
    MEDIA(1, "MEDIA"),
    ALTA(2, "ALTA");

    private Integer codigo;
    private String descricao;

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    PRIORIDADE(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static PRIORIDADE toEnum(Integer codigo) {
        if (Objects.isNull(codigo)) {
            return null;
        }

        for (PRIORIDADE prioridade : PRIORIDADE.values()) {
            if (prioridade.codigo.equals(codigo))
                return prioridade;
        }

        throw new IllegalArgumentException("Prioridade inválido");
    }
}