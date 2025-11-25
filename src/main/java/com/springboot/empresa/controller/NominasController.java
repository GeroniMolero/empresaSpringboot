package com.springboot.empresa.controller;

import com.service.INominaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.List;

@Controller
@RequestMapping("/nominas")
@RequiredArgsConstructor
public class NominasController {

    private final INominaService nominaService;

    @GetMapping("/formulario")
    public String mostrarFormulario() {
        return "salarioForm"; // plantilla Thymeleaf salarioForm.html
    }

    @PostMapping("/consultar")
    public String consultarSalario(
            @RequestParam String dni,
            Model model
    ) throws Exception {
        Map<String, Object> datos = nominaService.consultarSalarioEmpleado(dni);
        model.addAttribute("empleado", datos.get("empleado"));
        model.addAttribute("salario", datos.get("salario"));
        return "salarioResultado"; // plantilla Thymeleaf salarioResultado.html
    }

    @GetMapping("/listar")
    public String listarNominas(Model model) throws Exception {
        List<Map<String, Object>> listaNominas = nominaService.listarTodasLasNominas();
        model.addAttribute("listaNominas", listaNominas);
        return "nominas"; // plantilla Thymeleaf nominas.html
    }
}
