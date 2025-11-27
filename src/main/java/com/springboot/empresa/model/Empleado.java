package com.springboot.empresa.model;

import jakarta.persistence.*;

@Entity
@Table(name = "empleados")
public class Empleado extends Persona {

    @Id
    @Column(length = 9)
    private String dni;

    private int categoria;

    private int anyos;

    public Empleado() {}

    public Empleado(String nombre, String dni, String sexo, int categoria, int anyos) {
        super(nombre, sexo);
        this.dni = dni;
        this.categoria = categoria;
        this.anyos = anyos;
    }

    // === Getters & Setters ===
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public int getCategoria() { return categoria; }
    public void setCategoria(int categoria) { this.categoria = categoria; }

    public int getAnyos() { return anyos; }
    public void setAnyos(int anyos) { this.anyos = anyos; }

    // === Métodos ===
    public void incrAnyo() { this.anyos++; }
}
