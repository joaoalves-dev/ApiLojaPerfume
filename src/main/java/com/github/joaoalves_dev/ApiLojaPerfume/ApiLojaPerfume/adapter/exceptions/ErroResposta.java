package com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.adapter.exceptions;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErroResposta(
        int status,
        String mensagem,
        List<ErroCampo> erros
) {

    public static ErroResposta respostaPadrao(String mensagem) {
        return new ErroResposta(HttpStatus.BAD_REQUEST.value(), mensagem, List.of()); //retorna erro 400
    }

    public static ErroResposta conflito(String mensagem) {
        return new ErroResposta(HttpStatus.CONFLICT.value(), mensagem, List.of());
    }
}
