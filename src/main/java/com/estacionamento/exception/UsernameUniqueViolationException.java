package com.estacionamento.exception;

public class UsernameUniqueViolationException extends RuntimeException {
    public UsernameUniqueViolationException(String s) {
        super(s);
    }
}
