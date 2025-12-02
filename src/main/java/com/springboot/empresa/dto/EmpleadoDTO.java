package com.springboot.empresa.dto;

import jakarta.validation.constraints.*;

public class EmpleadoDTO {

    @NotBlank(message = "El DNI no puede estar vacío")
    @Size(min = 9, max = 9, message = "El DNI debe tener 9 caracteres")
    private String dni;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "El sexo no puede estar vacío")
    @Pattern(regexp = "^[MF]$", message = "El sexo debe ser 'M' o 'F'")
    private String sexo;

    @Min(value = 1, message = "La categoría debe ser como mínimo 1")
    @Max(value = 10, message = "La categoría debe ser como máximo 10")
    private int categoria;

    @Min(value = 0, message = "Los años no pueden ser negativos")
    private int anyos;

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
}