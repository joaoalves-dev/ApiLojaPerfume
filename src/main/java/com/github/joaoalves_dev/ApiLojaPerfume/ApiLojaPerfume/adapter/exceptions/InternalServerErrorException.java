package com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.adapter.exceptions;

public class InternalServerErrorException extends RuntimeException {
    public InternalServerErrorException(String message) {
        super(message);
    }

    public InternalServerErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
