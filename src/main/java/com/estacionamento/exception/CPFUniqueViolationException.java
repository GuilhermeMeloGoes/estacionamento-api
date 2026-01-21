package com.estacionamento.exception;

public class CPFUniqueViolationException extends RuntimeException {
    public CPFUniqueViolationException(String s) {
        super(s);
    }
}
