package com.backend.clinica_odontologica.service;

import com.backend.clinica_odontologica.dto.entrada.PacienteEntradaDto;
import com.backend.clinica_odontologica.dto.salida.PacienteSalidaDTO;
import com.backend.clinica_odontologica.exceptions.ResourceNotFoundException;


import java.util.List;

public interface IPacienteService {

    PacienteSalidaDTO registrarPaciente(PacienteEntradaDto pacienteEntradaDto);
    List<PacienteSalidaDTO> listarPacientes();

    PacienteSalidaDTO buscarPacientePorId(Long id);
    void eliminarPaciente(Long id) throws ResourceNotFoundException;
    PacienteSalidaDTO actualizarPaciente(PacienteEntradaDto pacienteEntradaDto, Long id);
}
