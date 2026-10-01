package com.example;


public class LimiteCreditoExcedidoException extends RuntimeException {
    public LimiteCreditoExcedidoException(String mensaje) {
        super(mensaje);
    }
}
