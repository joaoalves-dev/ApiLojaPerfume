package com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.application.domain.exceptions;

public class OperacaoNaoPermitidaException extends RuntimeException {
    public OperacaoNaoPermitidaException(String message) {
        super(message);
    }
}