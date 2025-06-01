package com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.application.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum FuncionarioCargo {
        ESTAGIARIO,
        VENDEDOR,
        GERENTE;

        @JsonCreator
        public static FuncionarioCargo fromString(String value) {
                for (FuncionarioCargo cargo : FuncionarioCargo.values()) {
                        if (cargo.name().equalsIgnoreCase(value)) {
                                return cargo;
                        }
                }
                throw new IllegalArgumentException("Cargo inválido: " + value);
        }

        @JsonValue
        public String toValue() {
                return this.name();
        }
}
