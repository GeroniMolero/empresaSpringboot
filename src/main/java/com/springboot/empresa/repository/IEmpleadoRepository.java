package com.springboot.empresa.repository;

import com.springboot.empresa.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IEmpleadoRepository extends JpaRepository<Empleado, String> {

    // Método para buscar por DNI
    Optional<Empleado> findByDni(String dni);

    List<Empleado> findByNombreContainingIgnoreCase(String nombre);
    List<Empleado> findBySexo(String sexo);
    List<Empleado> findByCategoria(int categoria);
    List<Empleado> findByAnyos(int anyos);
    void deleteByDni(String dni);
}