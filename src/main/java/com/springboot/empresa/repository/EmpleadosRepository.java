package com.springboot.empresa.repository;

import com.springboot.empresa.model.Empleado;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EmpleadosRepository {

    private final IEmpleadoRepository repo;

    public List<Empleado> findAll() {
        return repo.findAll();
    }

    public Optional<Empleado> findByDni(String dni) {
        return repo.findByDni(dni);
    }

    public Empleado save(Empleado empleado) {
        return repo.save(empleado);
    }
}
