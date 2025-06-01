package com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.adapter.dto;

import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.application.domain.enums.FuncionarioCargo;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record FuncionarioDTO(
        //@CPF para caso de producao onde iria validar realmente o cpf
        @Column(length = 14, nullable = false, unique = true)
        @NotBlank(message = "CPF é obrigatório")
        String cpf,

        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
        String nome,

        @NotNull(message = "Cargo é obrigatório")
        FuncionarioCargo cargo,

        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Email deve ser válido")
        @Size(max = 100, message = "Email deve ter no máximo 100 caracteres")
        String email
) {
}