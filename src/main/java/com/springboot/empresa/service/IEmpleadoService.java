package com.springboot.empresa.service;

import com.springboot.empresa.model.Empleado;

import java.util.List;

public interface IEmpleadoService {

    List<Empleado> listarEmpleados();

    Empleado buscarEmpleadoPorDni(String dni);

    List<Empleado> buscarEmpleadosPorCriterio(String texto);

    void actualizarEmpleado(Empleado empleado);

    // =================== MÉTODO AÑADIDO ===================
    /**
     * Guarda un nuevo empleado en la base de datos.
     * @param empleado El objeto Empleado a guardar.
     */
    void guardarEmpleado(Empleado empleado);

    // ... resto de la interfaz

    /**
     * Busca empleados por un campo y un valor específicos.
     * @param campo El campo por el que buscar (dni, nombre, sexo, etc.).
     * @param valor El valor a buscar en ese campo.
     * @return Una lista de empleados que coinciden.
     */
    List<Empleado> buscarEmpleadoPorCampoYValor(String campo, String valor);

    // ... resto de la interfaz
}