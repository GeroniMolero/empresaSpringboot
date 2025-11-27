package com.springboot.empresa.controller;

import com.springboot.empresa.model.Nomina;
import com.springboot.empresa.service.NominaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/nominas")
public class NominasController {

    @Autowired
    private NominaService nominaService;

    // Obtener salario de un empleado
    @GetMapping("/salario/{dni}")
    public Map<String, Object> consultarSalario(@PathVariable String dni) {
        double sueldo = nominaService.consultarSalario(dni);
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("dni", dni);
        respuesta.put("sueldo", sueldo);
        return respuesta;
    }

    // Listar todas las nóminas
    @GetMapping("/todas")
    public List<Map<String, Object>> listarNominas() {
        List<Nomina> nominas = nominaService.listarNominas();

        // Convertimos cada Nomina a Map<String,Object> para JSON
        return nominas.stream().map(n -> {
            Map<String, Object> map = new HashMap<>();
            map.put("dni", n.getDni());
            map.put("sueldo", n.getSueldo());
            return map;
        }).collect(Collectors.toList());
    }

    // Actualizar sueldo de un empleado
    @PutMapping("/actualizar/{dni}")
    public Map<String, Object> actualizarSueldo(@PathVariable String dni, @RequestParam double sueldo) {
        nominaService.actualizarSueldo(dni, sueldo);
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Sueldo actualizado correctamente");
        respuesta.put("dni", dni);
        respuesta.put("nuevoSueldo", sueldo);
        return respuesta;
    }
}
