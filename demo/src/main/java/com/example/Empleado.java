package com.example;

public class Empleado extends Persona {
    private double salario;
    private PuestoEmpleado puesto;
    private String fechaIngreso;
    private Departamento departamento;

    public Empleado(String nombre, String domicilio, String dni, String telefono,
                     double salario, PuestoEmpleado puesto, String fechaIngreso) {
        super(nombre, domicilio, dni, telefono);
        this.salario = salario;
        this.puesto = puesto;
        this.fechaIngreso = fechaIngreso;
    }

    public double getSalario() {
        return salario;
    }

    public PuestoEmpleado getPuesto() {
        return puesto;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    @Override
    public void mostrarInfo() {
        System.out.println("Empleado: " + datosBasicos()
                + " | Puesto: " + puesto
                + " | Departamento: " + (departamento != null ? departamento.getNombre() : "-"));
    }
}
