package com.backend.clinica_odontologica.service;

import com.backend.clinica_odontologica.dto.entrada.TurnoEntradaDto;
import com.backend.clinica_odontologica.dto.salida.TurnoSalidaDto;
import com.backend.clinica_odontologica.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ITurnoService {
    TurnoSalidaDto guardarTurno(TurnoEntradaDto turnoEntradaDto);
    List<TurnoSalidaDto> listarTurnos();

    TurnoSalidaDto buscarTurnoPorId(Long id);

    TurnoSalidaDto actualizarTurno(TurnoEntradaDto turnoEntradaDto, Long id);

    void eliminarTurno(Long id) throws ResourceNotFoundException;
}
