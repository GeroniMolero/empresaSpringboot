package com.springboot.empresa.service;

import com.springboot.empresa.model.Empleado;
import com.springboot.empresa.model.Nomina;
import com.springboot.empresa.repository.NominaRepository;
import lombok.RequiredArgsConstructor; // <-- Cambiado a Lombok para consistencia
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // <-- Añadido

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor // Recomendado sobre @Autowired
public class NominaService implements INominaService {

    private final NominaRepository nominaRepository;

    @Override
    @Transactional // Es buena práctica que las modificaciones sean transaccionales
    public void actualizarSueldo(String dni, double sueldo) {
        Optional<Nomina> optionalNomina = nominaRepository.findById(dni);
        if (optionalNomina.isPresent()) {
            Nomina nomina = optionalNomina.get();
            nomina.setSueldo(sueldo);
            nominaRepository.save(nomina);
        } else {
            // Si no existe, la creamos
            Nomina nueva = new Nomina(dni, sueldo);
            nominaRepository.save(nueva);
        }
    }

    @Override
    public double consultarSalario(String dni) {
        return nominaRepository.findById(dni)
                .map(Nomina::getSueldo)
                .orElse(0.0);
    }

    @Override
    public List<Nomina> listarNominas() {
        return nominaRepository.findAll();
    }

    @Override
    @Transactional
    public void calcularYGuardarNomina(Empleado empleado) {
        // 1. Calcular el sueldo usando la lógica de tu modelo Nomina
        double sueldoCalculado = new Nomina().sueldoCalculado(empleado);

        // 2. Usar el método existente actualizarSueldo para guardarlo.
        //    Este método ya se encarga de crear la nómina si no existe.
        this.actualizarSueldo(empleado.getDni(), sueldoCalculado);
    }

    @Override
    @Transactional
    public void borrarNominaPorDni(String dni) {
        nominaRepository.deleteById(dni);
    }
}