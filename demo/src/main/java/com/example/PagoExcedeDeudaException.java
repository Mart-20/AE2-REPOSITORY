package com.example;


public class PagoExcedeDeudaException extends RuntimeException {
    public PagoExcedeDeudaException(String mensaje) {
        super(mensaje);
    }
}
