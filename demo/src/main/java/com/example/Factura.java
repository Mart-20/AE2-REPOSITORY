package com.example;

import java.util.ArrayList;
import java.util.List;


public class Factura implements Descontable, Notificable, Comparable<Factura> {
    private int numero;
    private String fecha;
    private Cliente cliente;
    private Empleado empleado;
    private List<ProductoServicio> items;
    private List<Pago> pagos;

    public Factura(int numero, String fecha, Cliente cliente, Empleado empleado) {
        this.numero = numero;
        this.fecha = fecha;
        this.cliente = cliente;
        this.empleado = empleado;
        this.items = new ArrayList<>();
        this.pagos = new ArrayList<>();
    }

    public int getNumero() {
        return numero;
    }

    public String getFecha() {
        return fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<ProductoServicio> getItems() {
        return items;
    }

    public List<Pago> getPagos() {
        return pagos;
    }

    public void agregarItem(ProductoServicio item) {
        items.add(item);
    }

    public double calcularTotal() {
        double total = 0;
        for (ProductoServicio item : items) {
            total += item.getPrecio();
        }
        return total;
    }

    public double saldoPendiente() {
        double pagado = 0;
        for (Pago p : pagos) {
            pagado += p.getMonto();
        }
        return calcularTotal() - pagado;
    }

    
    public void emitir() {
        if (items.isEmpty()) {
            throw new FacturaSinItemsException(
                "La factura N° " + numero + " no se puede emitir sin items.");
        }
        System.out.println("Factura N° " + numero + " emitida. Total: $" + calcularTotal());
    }

    
    public void registrarPago(Pago pago) {
        if (pago.getMonto() > saldoPendiente()) {
            throw new PagoExcedeDeudaException(
                "El pago de $" + pago.getMonto() + " supera el saldo pendiente de $" + saldoPendiente()
                + " en la factura N° " + numero);
        }
        pago.procesar();
        pagos.add(pago);
        if (saldoPendiente() == 0) {
            notificar("Factura N° " + numero + " fue cancelada en su totalidad.");
        }
    }

    public void mostrarDetalle() {
        System.out.println("----- Factura N° " + numero + " (" + fecha + ") -----");
        cliente.mostrarInfo();
        empleado.mostrarInfo();
        System.out.println("Items:");
        for (ProductoServicio item : items) {
            System.out.println(" - " + item);
        }
        System.out.println("Total: $" + calcularTotal() + " | Saldo pendiente: $" + saldoPendiente());
    }

    @Override
    public double aplicarDescuento(double porcentaje) {
        double real = cliente.aplicarDescuento(porcentaje); 
        double nuevoTotal = calcularTotal() - (calcularTotal() * real / 100.0);
        System.out.println("Factura N° " + numero + " con descuento queda en $" + nuevoTotal);
        return nuevoTotal;
    }

    @Override
    public void notificar(String mensaje) {
        System.out.println("[Notificacion sobre factura N° " + numero + "]: " + mensaje);
    }

    // por monto total, de menor a mayor.
    @Override
    public int compareTo(Factura otra) {
        return Double.compare(this.calcularTotal(), otra.calcularTotal());
    }

    @Override
    public String toString() {
        return "Factura N° " + numero + " - " + fecha + " - Total: $" + calcularTotal();
    }
}
