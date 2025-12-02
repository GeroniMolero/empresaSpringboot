package com.springboot.empresa.dto;

import java.math.BigDecimal;

public class EmpleadoResponseDTO {
    private String dni;
    private String nombre;
    private String sexo;
    private int categoria;
    private int anyos;
    private BigDecimal sueldo;

    // Constructor
    public EmpleadoResponseDTO(String dni, String nombre, String sexo, int categoria, int anyos, double sueldo) {
        this.dni = dni;
        this.nombre = nombre;
        this.sexo = sexo;
        this.categoria = categoria;
        this.anyos = anyos;
        this.sueldo = BigDecimal.valueOf(sueldo);
    }

    // Getters y Setters
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }
    public int getCategoria() { return categoria; }
    public void setCategoria(int categoria) { this.categoria = categoria; }
    public int getAnyos() { return anyos; }
    public void setAnyos(int anyos) { this.anyos = anyos; }
    public BigDecimal getSueldo() { return sueldo; }
    public void setSueldo(BigDecimal sueldo) { this.sueldo = sueldo; }
}