package com.springboot.empresa.model;

import jakarta.persistence.*;

@Entity
@Table(name = "nominas")
public class Nomina {

    @Id
    @Column(length = 9)
    private String dni;

    private double sueldo;

    @Transient
    private static final int[] SUELDO_BASE = {
        50000, 70000, 90000, 110000, 130000,
        150000, 170000, 190000, 210000, 230000
    };

    public Nomina() {}

    public Nomina(String dni, double sueldo) {
        this.dni = dni;
        this.sueldo = sueldo;
    }

    // Cálculo original
    public double sueldoCalculado(Empleado e) {
        return SUELDO_BASE[e.getCategoria() - 1] + (5000 * e.getAnyos());
    }

    // === Getters & Setters ===
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public double getSueldo() { return sueldo; }
    public void setSueldo(double sueldo) { this.sueldo = sueldo; }
}
