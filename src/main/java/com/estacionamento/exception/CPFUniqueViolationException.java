package com.estacionamento.exception;

public class CPFUniqueViolationException extends RuntimeException {
    public CPFUniqueViolationException(String mensagem) {
        super(mensagem);
    }
}
