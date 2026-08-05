package com.erp.kicksotck.exceptions;

public class BadCredentialsException extends RuntimeException {
    public BadCredentialsException() {
        super("Credenciais inválidas, verifique as informações e tente novamente.");
    }

    public BadCredentialsException(String message) {super(message);}

}
