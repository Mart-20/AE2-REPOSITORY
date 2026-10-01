package com.example;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Persona implements Notificable, Descontable {
    private double limiteCredito;
    private double creditoUsado;
    private CategoriaCliente categoria;
    private List<Factura> historialCompras;

    public Cliente(String nombre, String domicilio, String dni, String telefono,
                   double limiteCredito, CategoriaCliente categoria) {
        super(nombre, domicilio, dni, telefono);
        this.limiteCredito = limiteCredito;
        this.categoria = categoria;
        this.creditoUsado = 0;
        this.historialCompras = new ArrayList<>();
    }

    public double getLimiteCredito() {
        return limiteCredito;
    }

    public CategoriaCliente getCategoria() {
        return categoria;
    }

    public List<Factura> getHistorialCompras() {
        return historialCompras;
    }

   
    public void registrarCompra(Factura factura) {
        double disponible = limiteCredito - creditoUsado;
        if (factura.calcularTotal() > disponible) {
            throw new LimiteCreditoExcedidoException(
                "El cliente " + getNombre() + " no tiene credito disponible suficiente. "
                + "Disponible: " + disponible + ", Compra: " + factura.calcularTotal());
        }
        creditoUsado += factura.calcularTotal();
        historialCompras.add(factura);
    }

    @Override
    public void mostrarInfo() {
        System.out.println("Cliente: " + datosBasicos()
                + " | Categoria: " + categoria
                + " | Credito usado: " + creditoUsado + "/" + limiteCredito);
    }

    @Override
    public void notificar(String mensaje) {
        System.out.println("[Notificacion a " + getNombre() + "]: " + mensaje);
    }

    @Override
    public double aplicarDescuento(double porcentaje) {
        // Ejemplo: los clientes PREMIUM/CORPORATIVO tienen un tope de descuento mayor.
        double tope = (categoria == CategoriaCliente.REGULAR) ? 10 : 25;
        double real = Math.min(porcentaje, tope);
        System.out.println(getNombre() + " recibe un descuento del " + real + "%");
        return real;
    }
}
