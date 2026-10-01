package com.example;

import java.util.ArrayList;
import java.util.List;

public class Proveedor extends Persona {
    private String razonSocial;
    private String numeroIdentificacionFiscal;
    private List<ProductoServicio> items;

    public Proveedor(String nombre, String domicilio, String dni, String telefono,
                      String razonSocial, String numeroIdentificacionFiscal) {
        super(nombre, domicilio, dni, telefono);
        this.razonSocial = razonSocial;
        this.numeroIdentificacionFiscal = numeroIdentificacionFiscal;
        this.items = new ArrayList<>();
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public String getNumeroIdentificacionFiscal() {
        return numeroIdentificacionFiscal;
    }

    public List<ProductoServicio> getItems() {
        return items;
    }

    public void agregarItem(ProductoServicio item) {
        items.add(item);
    }

    @Override
    public void mostrarInfo() {
        System.out.println("Proveedor: " + razonSocial + " (CUIT " + numeroIdentificacionFiscal + ")");
    }
}
