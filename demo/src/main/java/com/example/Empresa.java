package com.example;

import com.example.persistencia.FacturaRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Empresa {
    private List<Persona> personas;
    private List<Factura> facturas;
    private List<Departamento> departamentos;
    private FacturaRepository repositorio;

    public Empresa() {
        this.personas = new ArrayList<>();
        this.facturas = new ArrayList<>();
        this.departamentos = new ArrayList<>();
        this.repositorio = new FacturaRepository();
    }

    public void agregarPersona(Persona p) {
        personas.add(p);
    }

    public void agregarDepartamento(Departamento d) {
        departamentos.add(d);
    }

    public void agregarFactura(Factura f) {
        facturas.add(f);
    }

    /**
     * Elimina una factura de la lista en memoria segun su numero.
     * Devuelve true si la encontro y elimino, false si no existia.
     * No toca el archivo: para que el borrado quede reflejado en disco,
     * hay que llamar a guardarFacturas() despues (asi el usuario decide
     * cuando persistir el cambio, en vez de reescribir el archivo solo).
     */
    public boolean eliminarFactura(int numero) {
        return facturas.removeIf(f -> f.getNumero() == numero);
    }

    public void mostrarFacturas() {
        if (facturas.isEmpty()) {
            System.out.println("No hay facturas cargadas.");
            return;
        }
        System.out.println("--- Facturas actuales ---");
        for (Factura f : facturas) {
            System.out.println(f);
        }
    }

    public void mostrarPersonas() {
        for (Persona p : personas) {
            p.mostrarInfo(); // polimorfismo
        }
    }

    public void mostrarFacturasOrdenadasPorMonto() {
        List<Factura> copia = new ArrayList<>(facturas);
        Collections.sort(copia); // usa el Comparable natural de Factura (por monto)
        System.out.println("--- Facturas ordenadas por monto ---");
        for (Factura f : copia) {
            System.out.println(f);
        }
    }

    public void mostrarFacturasOrdenadasPorFecha() {
        List<Factura> copia = new ArrayList<>(facturas);
        Collections.sort(copia, new FacturaPorFechaComparator());
        System.out.println("--- Facturas ordenadas por fecha ---");
        for (Factura f : copia) {
            System.out.println(f);
        }
    }

    public void guardarFacturas() {
        try {
            repositorio.guardarTodas(facturas);
            System.out.println("Facturas guardadas en datos/facturas.txt");
        } catch (IOException e) {
            System.out.println("No se pudieron guardar las facturas: " + e.getMessage());
        }
    }

    public void mostrarFacturasGuardadas() {
        try {
            List<String> lineas = repositorio.leerTodas();
            System.out.println("--- Facturas guardadas en archivo ---");
            for (String linea : lineas) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("No se pudieron leer las facturas: " + e.getMessage());
        }
    }
}
