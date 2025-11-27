package com.springboot.empresa.service;

import com.springboot.empresa.model.Nomina;
import java.util.List;

public interface INominaService {

    /**
     * Actualiza el sueldo de la nómina de un empleado dado su DNI.
     * @param dni DNI del empleado
     * @param nuevoSueldo Nuevo sueldo a asignar
     */
    void actualizarSueldo(String dni, double nuevoSueldo);

    /**
     * Consulta el salario de un empleado dado su DNI.
     * @param dni DNI del empleado
     * @return Salario del empleado, o 0.0 si no existe
     */
    double consultarSalario(String dni);

    /**
     * Lista todas las nóminas registradas.
     * @return Lista de nóminas
     */
    List<Nomina> listarNominas();
}
