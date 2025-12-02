package com.springboot.empresa.controller;

import com.springboot.empresa.model.Empleado;
import com.springboot.empresa.model.Nomina;
import com.springboot.empresa.service.IEmpleadoService;
import com.springboot.empresa.service.INominaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/nominas")
@RequiredArgsConstructor
public class NominasWebController {

    private final INominaService nominaService;
    private final IEmpleadoService empleadoService;

    // ========================= FORMULARIO PARA CONSULTAR SALARIO =========================
    // Muestra salarioForm.html
    @GetMapping("/formularioSalario")
    public String mostrarFormularioSalario() {
        return "salarioForm";
    }

    // ========================= CONSULTAR SALARIO (desde el formulario) =========================
    // Procesa el formulario y muestra salarioResultado.html
    @PostMapping("/consultarSalario")
    public String consultarSalarioDesdeFormulario(@RequestParam String dni, Model model) {
        try {
            Empleado empleado = empleadoService.buscarEmpleadoPorDni(dni);
            double salario = nominaService.consultarSalario(dni);
            model.addAttribute("empleado", empleado);
            model.addAttribute("salario", salario);
            return "salarioResultado";
        } catch (Exception e) {
            model.addAttribute("error", "No se encontró el empleado con DNI: " + dni);
            return "salarioForm"; // Vuelve al formulario con un error
        }
    }

    // ========================= CONSULTAR SALARIO (desde un enlace directo) =========================
    // Muestra salarioResultado.html para un DNI específico
    @GetMapping("/consultarSalario/{dni}")
    public String consultarSalarioDesdeEnlace(@PathVariable String dni, Model model) {
        try {
            Empleado empleado = empleadoService.buscarEmpleadoPorDni(dni);
            double salario = nominaService.consultarSalario(dni);
            model.addAttribute("empleado", empleado);
            model.addAttribute("salario", salario);
            return "salarioResultado";
        } catch (Exception e) {
            model.addAttribute("error", "No se encontró el empleado con DNI: " + dni);
            return "error"; // Muestra la página de error genérica
        }
    }

    // ========================= LISTAR TODAS LAS NÓMINAS (VISTA WEB) =========================
    // Muestra nominas.html
    @GetMapping("/lista")
    public String listarNominasVista(Model model) {
        List<Nomina> listaNominas = nominaService.listarNominas();
        model.addAttribute("listaNominas", listaNominas);
        return "nominas";
    }
}