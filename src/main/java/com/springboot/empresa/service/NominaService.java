package com.springboot.empresa.service;

import com.springboot.empresa.model.Nomina;
import com.springboot.empresa.repository.NominaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NominaService implements INominaService {

    @Autowired
    private NominaRepository nominaRepository;

    @Override
    public void actualizarSueldo(String dni, double sueldo) {
        Optional<Nomina> optionalNomina = nominaRepository.findById(dni);
        if (optionalNomina.isPresent()) {
            Nomina nomina = optionalNomina.get();
            nomina.setSueldo(sueldo);
            nominaRepository.save(nomina);
        } else {
            // Si no existe, podemos crearla
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
}
