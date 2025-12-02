package com.springboot.empresa.config;

import com.springboot.empresa.model.Empleado;
import com.springboot.empresa.service.IEmpleadoService;
import com.springboot.empresa.service.INominaService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final IEmpleadoService empleadoService;
    private final INominaService nominaService;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (!empleadoService.listarEmpleados().isEmpty()) {
            System.out.println("La base de datos ya contiene datos. No se insertarán nuevos registros.");
            return;
        }

        System.out.println("Cargando datos iniciales en la base de datos...");

        Empleado emp1 = new Empleado("Juan Pérez", "11111111A", "M", 4, 8);
        Empleado emp2 = new Empleado("María Gómez", "22222222B", "F", 6, 10);
        Empleado emp3 = new Empleado("Carlos López", "33333333C", "M", 3, 4);
        Empleado emp4 = new Empleado("Ana Fernández", "44444444D", "F", 9, 15);
        Empleado emp5 = new Empleado("Luis Ramírez", "55555555E", "M", 2, 2);

        // Guardamos los empleados (necesitarás añadir este método a tu interfaz y servicio)
        empleadoService.guardarEmpleado(emp1);
        empleadoService.guardarEmpleado(emp2);
        empleadoService.guardarEmpleado(emp3);
        empleadoService.guardarEmpleado(emp4);
        empleadoService.guardarEmpleado(emp5);

        // Usamos el nuevo método para calcular y guardar las nóminas
        nominaService.calcularYGuardarNomina(emp1);
        nominaService.calcularYGuardarNomina(emp2);
        nominaService.calcularYGuardarNomina(emp3);
        nominaService.calcularYGuardarNomina(emp4);
        nominaService.calcularYGuardarNomina(emp5);

        System.out.println("¡Datos iniciales cargados con éxito!");
    }
}