package com.estacionamento.exception;

public class CodigoUniqueViolationException extends RuntimeException {
    public CodigoUniqueViolationException(String s) {
        super(s);
    }
}
