package com.backend.clinica_odontologica.service.impl;

import com.backend.clinica_odontologica.dto.entrada.TurnoEntradaDto;
import com.backend.clinica_odontologica.dto.salida.TurnoSalidaDto;
import com.backend.clinica_odontologica.entity.Turno;
import com.backend.clinica_odontologica.exceptions.ResourceNotFoundException;
import com.backend.clinica_odontologica.repository.OdontologoRepository;
import com.backend.clinica_odontologica.repository.TurnoRepository;
import com.backend.clinica_odontologica.service.ITurnoService;
import com.backend.clinica_odontologica.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService implements ITurnoService {
    //se mapea de DTO a entidad y viceversa
    private final Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);
    private final OdontologoRepository odontologoRepository;
    private final TurnoRepository turnoRepository;
    private final ModelMapper modelMapper;

    public TurnoService(OdontologoRepository odontologoRepository, TurnoRepository turnoRepository, ModelMapper modelMapper) {
        this.odontologoRepository = odontologoRepository;
        this.turnoRepository = turnoRepository;
        this.modelMapper = modelMapper;
        configureMapping();
    }

    @Override
    public TurnoSalidaDto guardarTurno(TurnoEntradaDto turnoEntradaDto) {
        //logica de negocio
        //mapeo de dto a entidad
        LOGGER.info("TurnoEntradaDto: " + JsonPrinter.toString(turnoEntradaDto));
        Turno turno = modelMapper.map(turnoEntradaDto, Turno.class);
        LOGGER.info("TurnoEntidad: " + JsonPrinter.toString(turno));
        //Turno turnoRegistrado = turnoIDao.registrar(turno);
        //mapeo de entidad a dto
        TurnoSalidaDto turnoSalidaDto = modelMapper.map(turnoRepository.save(turno), TurnoSalidaDto.class);
        LOGGER.info("TurnoSalidaDto: " + JsonPrinter.toString(turnoSalidaDto));
        return turnoSalidaDto;
    }

    @Override
    public List<TurnoSalidaDto> listarTurnos() {
        //mapeo de lista de entidades a lista de dtos
        List<TurnoSalidaDto> turnos = turnoRepository.findAll()
                .stream()
                .map(turno -> modelMapper.map(turno, TurnoSalidaDto.class))
                .toList();
        LOGGER.info("Listado de todos los turnos: {}", JsonPrinter.toString(turnos));

        return turnos;
    }

    @Override
    public TurnoSalidaDto buscarTurnoPorId(Long id) {

        Turno turnoBuscado = turnoRepository.findById(id).orElse(null);
        TurnoSalidaDto turnoEncontrado = null;

        if (turnoBuscado != null){
            turnoEncontrado = modelMapper.map(turnoBuscado, TurnoSalidaDto.class);
            LOGGER.info("Turno encontrado: {}", JsonPrinter.toString(turnoEncontrado));
        } else LOGGER.error("No se ha encontrado el turno con id {}", id);

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
    public TurnoSalidaDto actualizarTurno(TurnoEntradaDto turnoEntradaDto, Long id) {

        Turno turnoRecibido = modelMapper.map(turnoEntradaDto, Turno.class);
        Turno turnoAActualizar = turnoRepository.findById(id).orElse(null);
        TurnoSalidaDto turnoSalidaDto = null;

        if(turnoAActualizar != null){

            turnoRecibido.setIdTurno(turnoAActualizar.getIdTurno());
            turnoAActualizar = turnoRecibido;

            //turnoAActualizar.setNombre(turnoRecibido.getNombre());
            //turnoAActualizar.setApellido(turnoRecibido.getApellido());
            //turnoAActualizar.setDni(turnoRecibido.getDni());
            //turnoAActualizar.setFechaIngreso(turnoRecibido.getFechaIngreso());
            //turnoAActualizar.getDomicilio().setNumero(turnoRecibido.getDomicilio().getNumero());
            //turnoAActualizar.getDomicilio().setLocalidad(turnoRecibido.getDomicilio().getLocalidad());
            //turnoAActualizar.getDomicilio().setProvincia(turnoRecibido.getDomicilio().getProvincia());

            turnoRepository.save(turnoAActualizar);
            turnoSalidaDto = modelMapper.map(turnoAActualizar, TurnoSalidaDto.class);
            LOGGER.warn("Turno actualizado: {}", JsonPrinter.toString(turnoSalidaDto));

        } else {
            LOGGER.error("No fue posible actualizar el turno porque no se encuentra en nuestra base de datos");
            //lanzar excepcion
        }

        return turnoSalidaDto;
    }


    private void configureMapping(){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.typeMap(TurnoEntradaDto.class, Turno.class)
                .addMappings(mapper -> mapper.map(TurnoEntradaDto::getIdOdontologo, Turno::setOdontologo))
                .addMappings(mapper -> mapper.map(TurnoEntradaDto::getIdPaciente, Turno::setPaciente));
        //modelMapper.typeMap(Turno.class, TurnoSalidaDto.class)
          //      .addMappings(mapper -> mapper.map(Turno::getDomicilio, TurnoSalidaDto::setDomicilioSalidaDto));
    }
}

