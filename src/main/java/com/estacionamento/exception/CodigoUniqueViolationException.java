package com.estacionamento.exception;

public class CodigoUniqueViolationException extends RuntimeException {
    public CodigoUniqueViolationException(String mensagem) {
        super(mensagem);
    }
}
