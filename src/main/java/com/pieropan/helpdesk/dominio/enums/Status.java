package com.pieropan.helpdesk.dominio.enums;

import java.util.Objects;

public enum Status {
    ABERTO(0, "ABERTO"),
    ANDAMENTO(1, "ANDAMENTO"),
    ENCERRADO(2, "ENCERRADO");

    private Integer codigo;
    private String descricao;

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    Status(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static Status toEnum(Integer codigo) {
        if (Objects.isNull(codigo)) {
            return null;
        }

        for (Status status : Status.values()) {
            if (status.codigo.equals(codigo))
                return status;
        }

        throw new IllegalArgumentException("Status inválido");
    }
}