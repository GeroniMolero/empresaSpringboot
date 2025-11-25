package com.springboot.empresa.controller;

import com.model.Empleado;
import com.service.IEmpleadoService;
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

    @GetMapping
    public String listarEmpleados(Model model) {
        List<Empleado> lista = empleadoService.listarEmpleados();
        model.addAttribute("listaEmpleados", lista);
        return "empleados";  // plantilla Thymeleaf empleados.html
    }

    @GetMapping("/buscar")
    public String mostrarFormularioBusqueda() {
        return "buscarEmpleado"; // buscarEmpleado.html
    }

    @PostMapping("/buscar")
    public String buscarResultado(
            @RequestParam String criterio,
            Model model
    ) {
        model.addAttribute("listaEmpleados",
                empleadoService.buscarEmpleadosPorCriterio(criterio));
        return "resultadoBusqueda"; // resultadoBusqueda.html
    }

    @GetMapping("/editar/{dni}")
    public String editarEmpleado(@PathVariable String dni, Model model) {

        Empleado emp = empleadoService.buscarEmpleadoPorDni(dni);

        if (emp == null) {
            model.addAttribute("error", "No se encontró el empleado con DNI: " + dni);
        } else {
            model.addAttribute("empleado", emp);
        }
        return "editarEmpleado";  // editarEmpleado.html
    }

    @PostMapping("/actualizar")
    public String actualizarEmpleado(@ModelAttribute Empleado empleado) {

        empleadoService.actualizarEmpleado(empleado);

        return "redirect:/empleados";
    }
}
