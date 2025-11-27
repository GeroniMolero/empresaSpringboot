package com.springboot.empresa.repository;

import com.springboot.empresa.model.Nomina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NominaRepository extends JpaRepository<Nomina, String> {
}
