package com.example;


public class ProductoServicio implements Descontable {
    private String codigo;
    private String nombre;
    private double precio;
    private TipoItem tipo;
    private Proveedor proveedor;

    public ProductoServicio(String codigo, String nombre, double precio, TipoItem tipo, Proveedor proveedor) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
        this.proveedor = proveedor;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public TipoItem getTipo() {
        return tipo;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    @Override
    public double aplicarDescuento(double porcentaje) {
        double nuevoPrecio = precio - (precio * porcentaje / 100.0);
        System.out.println(nombre + " baja de $" + precio + " a $" + nuevoPrecio);
        return nuevoPrecio;
    }

    @Override
    public String toString() {
        return nombre + " (" + tipo + ") - $" + precio;
    }
}
