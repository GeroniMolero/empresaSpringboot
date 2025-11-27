package com.springboot.empresa.service;

import com.springboot.empresa.model.Empleado;
import com.springboot.empresa.repository.IEmpleadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpleadoService implements IEmpleadoService {

    private final IEmpleadoRepository repo;

    @Override
    public List<Empleado> listarEmpleados() {
        return repo.findAll();
    }

    @Override
    public List<Empleado> buscarEmpleadosPorCriterio(String criterio) {
        final String texto = criterio.toLowerCase();
        return repo.findAll().stream()
                .filter(e ->
                        e.getNombre().toLowerCase().contains(texto) ||
                        e.getDni().toLowerCase().contains(texto) ||
                        e.getSexo().toLowerCase().contains(texto)
                )
                .toList();
    }

    @Override
    public Empleado buscarEmpleadoPorDni(String dni) {
        Optional<Empleado> optional = repo.findByDni(dni);
        return optional.orElse(null);
    }

    @Override
    public void actualizarEmpleado(Empleado empleado) {
        repo.save(empleado);
    }
}
