package com.example;


public abstract class Persona {
    private String nombre;
    private String domicilio;
    private String dni;
    private String telefono;

    public Persona(String nombre, String domicilio, String dni, String telefono) {
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.dni = dni;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public String getDni() {
        return dni;
    }

    public String getTelefono() {
        return telefono;
    }

    
    public String datosBasicos() {
        return nombre + " (DNI " + dni + ") - Tel: " + telefono;
    }

    
    public abstract void mostrarInfo();
}
