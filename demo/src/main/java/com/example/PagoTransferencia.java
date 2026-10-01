package com.example;

public class PagoTransferencia extends Pago {
    private String cbu;

    public PagoTransferencia(double monto, String fecha, String cbu) {
        super(monto, fecha, FormaPago.TRANSFERENCIA);
        this.cbu = cbu;
    }

    public String getCbu() {
        return cbu;
    }

    @Override
    public void procesar() {
        System.out.println("Transferencia registrada al CBU " + cbu + " por $" + getMonto()
                + " - queda en estado PARCIAL hasta confirmar acreditacion.");
        setEstado(EstadoPago.PARCIAL);
    }
}
