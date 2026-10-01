package com.example;

import java.util.Comparator;


public class FacturaPorFechaComparator implements Comparator<Factura> {
    @Override
    public int compare(Factura f1, Factura f2) {
        return f1.getFecha().compareTo(f2.getFecha());
    }
}
