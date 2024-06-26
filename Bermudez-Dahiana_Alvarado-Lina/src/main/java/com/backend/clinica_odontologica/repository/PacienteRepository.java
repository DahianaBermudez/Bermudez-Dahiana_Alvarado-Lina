package com.backend.clinica_odontologica.repository;

import com.backend.clinica_odontologica.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long>{
    Paciente findByDni(int dni);
    Paciente findByDniAndNombre(int dni, String nombre);

}
