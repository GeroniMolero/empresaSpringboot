package com.springboot.empresa.service;

import com.springboot.empresa.model.Empleado;

import java.util.List;

public interface IEmpleadoService {

    List<Empleado> listarEmpleados();

    Empleado buscarEmpleadoPorDni(String dni);

    List<Empleado> buscarEmpleadosPorCriterio(String texto);

    void actualizarEmpleado(Empleado empleado);
}
