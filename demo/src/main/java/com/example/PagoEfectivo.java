package com.example;

public class PagoEfectivo extends Pago {

    public PagoEfectivo(double monto, String fecha) {
        super(monto, fecha, FormaPago.EFECTIVO);
    }

    @Override
    public void procesar() {
        System.out.println("Pago en efectivo registrado por $" + getMonto() + " - sin intermediarios.");
        setEstado(EstadoPago.CANCELADO);
    }
}
