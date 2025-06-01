package com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.application.domain.exceptions;

import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.adapter.exceptions.ErroResposta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ValidacaoException {

    @ExceptionHandler(OperacaoNaoPermitidaException.class)
    public ResponseEntity<ErroResposta> handleOperacaoNaoPermitidaException(OperacaoNaoPermitidaException ex) {
        ErroResposta errorResponse = new ErroResposta(
                HttpStatus.FORBIDDEN.value(),
                ex.getMessage(),
                List.of()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

}