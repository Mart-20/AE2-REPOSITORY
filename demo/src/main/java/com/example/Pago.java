package com.example;


public abstract class Pago {
    private double monto;
    private String fecha;
    private FormaPago metodoPago;
    private EstadoPago estado;

    public Pago(double monto, String fecha, FormaPago metodoPago) {
        this.monto = monto;
        this.fecha = fecha;
        this.metodoPago = metodoPago;
        this.estado = EstadoPago.PENDIENTE;
    }

    public double getMonto() {
        return monto;
    }

    public String getFecha() {
        return fecha;
    }

    public FormaPago getMetodoPago() {
        return metodoPago;
    }

    public EstadoPago getEstado() {
        return estado;
    }

    public void setEstado(EstadoPago estado) {
        this.estado = estado;
    }

    
    public String resumen() {
        return "Pago [$" + monto + " - " + metodoPago + " - Estado: " + estado + "]";
    }

    
    public abstract void procesar();
}
