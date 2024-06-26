package com.backend.clinica_odontologica.service.impl;

import com.backend.clinica_odontologica.dto.entrada.TurnoEntradaDto;
import com.backend.clinica_odontologica.dto.salida.TurnoSalidaDto;
import com.backend.clinica_odontologica.entity.Odontologo;
import com.backend.clinica_odontologica.entity.Paciente;
import com.backend.clinica_odontologica.entity.Turno;
import com.backend.clinica_odontologica.exceptions.ResourceNotFoundException;
import com.backend.clinica_odontologica.repository.OdontologoRepository;
import com.backend.clinica_odontologica.repository.PacienteRepository;
import com.backend.clinica_odontologica.repository.TurnoRepository;
import com.backend.clinica_odontologica.service.ITurnoService;
import com.backend.clinica_odontologica.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService implements ITurnoService {

    private final Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);
    private final OdontologoRepository odontologoRepository;
    private final PacienteRepository pacienteRepository;
    private final TurnoRepository turnoRepository;
    private final ModelMapper modelMapper;

    public TurnoService(OdontologoRepository odontologoRepository, PacienteRepository pacienteRepository, TurnoRepository turnoRepository, ModelMapper modelMapper) {
        this.odontologoRepository = odontologoRepository;
        this.pacienteRepository = pacienteRepository;
        this.turnoRepository = turnoRepository;
        this.modelMapper = modelMapper;
        configureMapping();
    }

    @Override
    public TurnoSalidaDto guardarTurno(TurnoEntradaDto turnoEntradaDto) throws ResourceNotFoundException {
        LOGGER.info("TurnoEntradaDto: " + JsonPrinter.toString(turnoEntradaDto));
        Odontologo odontologo = this.odontologoRepository.findById(turnoEntradaDto.getIdOdontologo()).orElse(null);
        Paciente paciente = this.pacienteRepository.findById(turnoEntradaDto.getIdPaciente()).orElse(null);
        LOGGER.info("odontologo: " + JsonPrinter.toString(odontologo));
        LOGGER.info("paciente: " + JsonPrinter.toString(paciente));
        if(odontologo == null){
            throw new ResourceNotFoundException("No existe registro de odontologo con id " + turnoEntradaDto.getIdOdontologo());
        }
        if(paciente == null){
            throw new ResourceNotFoundException("No existe registro de paciente con id " + turnoEntradaDto.getIdPaciente());
        }
        Turno turno = new Turno();
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);
        turno.setFechaHora(turnoEntradaDto.getFechaHora());
        LOGGER.info("TurnoEntidad: " + JsonPrinter.toString(turno));
        TurnoSalidaDto turnoSalidaDto = modelMapper.map(turnoRepository.save(turno), TurnoSalidaDto.class);
        LOGGER.info("TurnoSalidaDto: " + JsonPrinter.toString(turnoSalidaDto));
        System.out.println(turnoSalidaDto);
        return turnoSalidaDto;
    }

    @Override
    public List<TurnoSalidaDto> listarTurnos() {
        List<TurnoSalidaDto> turnos = turnoRepository.findAll()
                .stream()
                .map(turno -> modelMapper.map(turno, TurnoSalidaDto.class))
                .toList();
        LOGGER.info("Listado de todos los turnos: {}", JsonPrinter.toString(turnos));

        return turnos;
    }

    @Override
    public TurnoSalidaDto buscarTurnoPorId(Long id) throws ResourceNotFoundException {

        Turno turnoBuscado = turnoRepository.findById(id).orElse(null);
        TurnoSalidaDto turnoEncontrado = null;

        if (turnoBuscado != null){
            turnoEncontrado = modelMapper.map(turnoBuscado, TurnoSalidaDto.class);
            LOGGER.info("Turno encontrado: {}", JsonPrinter.toString(turnoEncontrado));
        } else {LOGGER.error("No se ha encontrado el turno con id {}", id);
            throw new ResourceNotFoundException("No existe registro de turno con id " + id);}

        return turnoEncontrado;
    }

    @Override
    public void eliminarTurno(Long id) throws ResourceNotFoundException {
        if(buscarTurnoPorId(id) != null){
            turnoRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el turno con id {}", id);
        }  else {
            throw new ResourceNotFoundException("No existe registro de turno con id " + id);
        }

    }

    @Override
    public TurnoSalidaDto actualizarTurno(TurnoEntradaDto turnoEntradaDto, Long id) throws ResourceNotFoundException {

        LOGGER.info("TurnoEntradaDto: " + JsonPrinter.toString(turnoEntradaDto));
        Odontologo o = this.odontologoRepository.findById(turnoEntradaDto.getIdOdontologo()).orElse(null);
        Paciente p = this.pacienteRepository.findById(turnoEntradaDto.getIdPaciente()).orElse(null);
        LOGGER.info("odontologo: " + JsonPrinter.toString(o));
        LOGGER.info("paciente: " + JsonPrinter.toString(p));
        Turno turnoRecibido = new Turno();
        turnoRecibido.setPaciente(p);
        turnoRecibido.setOdontologo(o);
        turnoRecibido.setFechaHora(turnoEntradaDto.getFechaHora());
        LOGGER.info("TurnoEntidad: " + JsonPrinter.toString(turnoRecibido));
        Turno turnoAActualizar = turnoRepository.findByidTurno(id);
        TurnoSalidaDto turnoSalidaDto = null;

        if(turnoAActualizar != null){

            turnoRecibido.setIdTurno(turnoAActualizar.getIdTurno());
            turnoAActualizar = turnoRecibido;
            turnoRepository.save(turnoAActualizar);
            turnoSalidaDto = modelMapper.map(turnoAActualizar, TurnoSalidaDto.class);
            LOGGER.warn("Turno actualizado: {}", JsonPrinter.toString(turnoSalidaDto));

        } else {
            LOGGER.error("No fue posible actualizar el turno porque no se encuentra en nuestra base de datos");
            throw new ResourceNotFoundException("No existe registro de turno con id " + id);
        }

        return turnoSalidaDto;
    }


    private void configureMapping(){
        modelMapper.typeMap(Turno.class, TurnoSalidaDto.class)
                .addMappings(mapper -> mapper.map(Turno::getPacienteNombre, TurnoSalidaDto::setPaciente))
                .addMappings(mapper -> mapper.map(Turno::getFechaHora, TurnoSalidaDto::setFechaHora))
                .addMappings(mapper -> mapper.map(Turno::getOdontologoNombre, TurnoSalidaDto::setOdontologo));
    }
}

