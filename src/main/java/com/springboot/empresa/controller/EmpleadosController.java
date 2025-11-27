package com.springboot.empresa.controller;

import com.springboot.empresa.model.Empleado;
import com.springboot.empresa.service.IEmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/empleados")
@RequiredArgsConstructor
public class EmpleadosController {

    private final IEmpleadoService empleadoService;

    // ========================= LISTAR =========================
    @GetMapping
    public String listarEmpleados(Model model) {
        List<Empleado> lista = empleadoService.listarEmpleados();
        model.addAttribute("listaEmpleados", lista);
        return "empleados";  // empleados.html
    }

    // ========================= BUSCAR =========================
    @GetMapping("/buscar")
    public String mostrarFormularioBusqueda() {
        return "buscarEmpleado"; // buscarEmpleado.html
    }

    @PostMapping("/buscar")
    public String buscarResultado(@RequestParam String criterio, Model model) {

        List<Empleado> resultado = empleadoService.buscarEmpleadosPorCriterio(criterio);

        model.addAttribute("listaEmpleados", resultado);
        model.addAttribute("criterio", criterio);

        return "resultadoBusqueda"; // resultadoBusqueda.html
    }

    // ========================= EDITAR EMPLEADO =========================
    @GetMapping("/editar/{dni}")
    public String editarEmpleado(@PathVariable String dni, Model model) {

        Empleado emp = empleadoService.buscarEmpleadoPorDni(dni);

        if (emp == null) {
            model.addAttribute("error", "No se encontró el empleado con DNI: " + dni);
            return "error"; // error.html
        }

        model.addAttribute("empleado", emp);
        return "editarEmpleado"; // editarEmpleado.html
    }

    // ========================= ACTUALIZAR EMPLEADO =========================
    @PostMapping("/actualizar")
    public String actualizarEmpleado(@ModelAttribute Empleado empleado, Model model) {

        try {
            empleadoService.actualizarEmpleado(empleado);
        } catch (Exception e) {
            model.addAttribute("error", "No se pudo actualizar el empleado: " + e.getMessage());
            model.addAttribute("empleado", empleado);
            return "editarEmpleado";
        }

        return "redirect:/empleados";
    }
}
