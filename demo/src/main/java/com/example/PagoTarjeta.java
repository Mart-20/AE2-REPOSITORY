package com.example;

public class PagoTarjeta extends Pago {
    private String numeroTarjeta;

    public PagoTarjeta(double monto, String fecha, String numeroTarjeta) {
        super(monto, fecha, FormaPago.TARJETA);
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    @Override
    public void procesar() {
        System.out.println("Procesando pago con tarjeta terminada en "
                + numeroTarjeta.substring(numeroTarjeta.length() - 4) + " por $" + getMonto());
        setEstado(EstadoPago.CANCELADO);
    }
}
