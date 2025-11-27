package com.springboot.empresa.model;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Persona {

    private String nombre;
    private String sexo;

    public Persona() {}

    public Persona(String nombre, String sexo) {
        this.nombre = nombre;
        this.sexo = sexo;
    }

    // === Getters & Setters ===
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }
}
