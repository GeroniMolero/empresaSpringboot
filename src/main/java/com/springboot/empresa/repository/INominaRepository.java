package com.springboot.empresa.repository;

import com.springboot.empresa.model.Nomina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INominaRepository extends JpaRepository<Nomina, String> {
}
