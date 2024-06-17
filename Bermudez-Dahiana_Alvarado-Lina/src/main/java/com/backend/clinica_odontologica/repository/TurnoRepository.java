package com.backend.clinica_odontologica.repository;

import com.backend.clinica_odontologica.entity.Paciente;
import com.backend.clinica_odontologica.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurnoRepository extends JpaRepository<Turno, Long>{
    Turno findByIdTurno(int id);

}
