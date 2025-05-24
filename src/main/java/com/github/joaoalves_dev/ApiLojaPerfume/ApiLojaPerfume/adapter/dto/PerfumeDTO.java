package com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.adapter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record PerfumeDTO(
        @NotBlank(message = "O nome é obrigatório")
        @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
        String nome,

        @NotBlank(message = "A marca é obrigatória")
        String marca,

        @NotNull(message = "O volume é obrigatório")
        @Positive(message = "O volume deve ser positivo")
        Double volume,

        @NotBlank(message = "O gênero é obrigatório")
        String genero,

        @NotNull(message = "O preço é obrigatório")
        @Positive(message = "O preço deve ser positivo")
        Double preco
) {}