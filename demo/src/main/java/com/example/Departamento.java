package com.example;


public class Departamento implements Notificable {
    private String nombre;
    private double presupuesto;
    private Empleado responsable;

    public Departamento(String nombre, double presupuesto, Empleado responsable) {
        this.nombre = nombre;
        this.presupuesto = presupuesto;
        this.responsable = responsable;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public Empleado getResponsable() {
        return responsable;
    }

    @Override
    public void notificar(String mensaje) {
        System.out.println("[Notificacion al departamento " + nombre + "]: " + mensaje);
    }
}
