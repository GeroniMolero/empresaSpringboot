package com.springboot.empresa.service;

import com.springboot.empresa.model.Empleado;
import com.springboot.empresa.repository.IEmpleadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpleadoService implements IEmpleadoService {

    private final IEmpleadoRepository repo;
    private final INominaService nominaService;

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
    @Transactional
    public void actualizarEmpleado(Empleado empleado) {
        repo.save(empleado);
        nominaService.calcularYGuardarNomina(empleado);
    }

    @Override
    @Transactional
    public void guardarEmpleado(Empleado empleado) {
        repo.save(empleado);
    }

    @Override
    public List<Empleado> buscarEmpleadoPorCampoYValor(String campo, String valor) {
        switch (campo.toLowerCase()) {
            case "dni":
                return repo.findByDni(valor).map(List::of).orElse(List.of());
            case "nombre":
                return repo.findByNombreContainingIgnoreCase(valor);
            case "sexo":
                return repo.findBySexo(valor);
            case "categoria":
                try {
                    int categoria = Integer.parseInt(valor);
                    return repo.findByCategoria(categoria);
                } catch (NumberFormatException e) {
                    return List.of();
                }
            case "anyos":
                try {
                    int anyos = Integer.parseInt(valor);
                    return repo.findByAnyos(anyos);
                } catch (NumberFormatException e) {
                    return List.of();
                }
            default:
                return List.of();
        }
    }

    @Override
    @Transactional
    public void borrarEmpleadoPorDni(String dni) {
        // Primero borramos la nómina asociada para evitar problemas de clave foránea
        nominaService.borrarNominaPorDni(dni); // Necesitarás añadir este método a INominaService
        // Luego borramos al empleado
        repo.deleteByDni(dni);
    }
}