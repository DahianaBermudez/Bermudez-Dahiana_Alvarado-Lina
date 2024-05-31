package com.backend.clinica_odontologica.service.impl;

import com.backend.clinica_odontologica.dto.entrada.PacienteEntradaDto;
import com.backend.clinica_odontologica.dto.salida.PacienteSalidaDto;
import com.backend.clinica_odontologica.entity.Paciente;
import com.backend.clinica_odontologica.repository.IDao;
import com.backend.clinica_odontologica.service.IPacienteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService implements IPacienteService {

    private IDao<Paciente> pacienteIDao;

    public PacienteService(IDao<Paciente> pacienteIDao) {
        this.pacienteIDao = pacienteIDao;
    }

    @Override
    public PacienteSalidaDto registrarPaciente(PacienteEntradaDto pacienteEntradaDto) {
        //Logica de negocio
        //Mapeo de dtos a entidad
        //pacienteIDao.registrar(pacienteEntradaDto);
        // mapeo entidad dto
        return null;
    }

    @Override
    public List<PacienteSalidaDto> listarPacientes() {
        //pacienteIDao.listarTodos();
        //mapeo entidad a dto
        return null;
    }
}
