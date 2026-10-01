package com.example.persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.Factura;


public class FacturaRepository {
    private final File archivo = new File("datos/facturas.txt");

    public void guardarTodas(List<Factura> facturas) throws IOException {
        archivo.getParentFile().mkdirs();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (Factura f : facturas) {
                String linea = f.getNumero() + ";" + f.getFecha() + ";"
                        + f.getCliente().getNombre() + ";" + f.calcularTotal();
                writer.write(linea);
                writer.newLine();
            }
        }
    }

    public List<String> leerTodas() throws IOException {
        List<String> lineas = new ArrayList<>();
        if (!archivo.exists()) {
            return lineas; // todavia no se guardo nada: devolvemos lista vacia
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                lineas.add(linea);
            }
        }
        return lineas;
    }
}
