package com.springboot.empresa.controller;

import com.springboot.empresa.dto.EmpleadoDTO;
import com.springboot.empresa.dto.EmpleadoResponseDTO;
import com.springboot.empresa.model.Empleado;
import com.springboot.empresa.service.IEmpleadoService;
import com.springboot.empresa.service.INominaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/empleados")
@RequiredArgsConstructor
public class EmpleadoRestController {

    private final IEmpleadoService empleadoService;
    private final INominaService nominaService;

    // --- LISTAR TODOS ---
    @GetMapping
    public List<EmpleadoResponseDTO> listarTodos() {
        return empleadoService.listarEmpleados().stream()
                .map(this::convertirAResponseDTO)
                .collect(Collectors.toList());
    }

    // --- BUSCAR POR DNI ---
    @GetMapping("/{dni}")
    public ResponseEntity<EmpleadoResponseDTO> buscarPorDni(@PathVariable String dni) {
        Empleado empleado = empleadoService.buscarEmpleadoPorDni(dni);
        if (empleado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(convertirAResponseDTO(empleado), HttpStatus.OK);
    }

    // --- CREAR EMPLEADO ---
    @PostMapping
    public ResponseEntity<EmpleadoResponseDTO> crearEmpleado(@Valid @RequestBody EmpleadoDTO empleadoDTO) {
        Empleado nuevoEmpleado = new Empleado(
                empleadoDTO.getNombre(),
                empleadoDTO.getDni(),
                empleadoDTO.getSexo(),
                empleadoDTO.getCategoria(),
                empleadoDTO.getAnyos()
        );
        empleadoService.guardarEmpleado(nuevoEmpleado);
        nominaService.calcularYGuardarNomina(nuevoEmpleado);

        EmpleadoResponseDTO response = convertirAResponseDTO(nuevoEmpleado);
        return new ResponseEntity<>(response, HttpStatus.CREATED); // 201 Created
    }

    // --- ACTUALIZAR EMPLEADO ---
    @PutMapping("/{dni}")
    public ResponseEntity<EmpleadoResponseDTO> actualizarEmpleado(
            @PathVariable String dni, @Valid @RequestBody EmpleadoDTO empleadoDTO) {

        Empleado empleadoExistente = empleadoService.buscarEmpleadoPorDni(dni);
        if (empleadoExistente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Actualizamos los datos del empleado existente
        empleadoExistente.setNombre(empleadoDTO.getNombre());
        empleadoExistente.setSexo(empleadoDTO.getSexo());
        empleadoExistente.setCategoria(empleadoDTO.getCategoria());
        empleadoExistente.setAnyos(empleadoDTO.getAnyos());

        empleadoService.actualizarEmpleado(empleadoExistente);

        return new ResponseEntity<>(convertirAResponseDTO(empleadoExistente), HttpStatus.OK);
    }

    // --- BORRAR EMPLEADO ---
    @DeleteMapping("/{dni}")
    public ResponseEntity<Void> borrarEmpleado(@PathVariable String dni) {
        Empleado empleado = empleadoService.buscarEmpleadoPorDni(dni);
        if (empleado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        empleadoService.borrarEmpleadoPorDni(dni);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
    }

    // Método auxiliar para convertir Entidad a DTO de respuesta
    private EmpleadoResponseDTO convertirAResponseDTO(Empleado empleado) {
        double sueldo = nominaService.consultarSalario(empleado.getDni());
        return new EmpleadoResponseDTO(
                empleado.getDni(),
                empleado.getNombre(),
                empleado.getSexo(),
                empleado.getCategoria(),
                empleado.getAnyos(),
                sueldo
        );
    }
}