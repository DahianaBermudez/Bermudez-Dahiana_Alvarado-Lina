package com.backend.clinica_odontologica.service.impl;

import com.backend.clinica_odontologica.dto.entrada.OdontologoEntradaDto;
import com.backend.clinica_odontologica.dto.salida.OdontologoSalidaDto;
import com.backend.clinica_odontologica.entity.Odontologo;
import com.backend.clinica_odontologica.exceptions.ResourceNotFoundException;
import com.backend.clinica_odontologica.repository.OdontologoRepository;
import com.backend.clinica_odontologica.service.IOdontologoService;
import com.backend.clinica_odontologica.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {
    private final Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);
    private final OdontologoRepository odontologoRepository;
    private final ModelMapper modelMapper;

    public OdontologoService(OdontologoRepository odontologoRepository, ModelMapper modelMapper) {

        this.odontologoRepository = odontologoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public OdontologoSalidaDto guardarOdontologo(OdontologoEntradaDto odontologoEntradaDto) {
        //logica de negocio
        //mapeo de dto a entidad
        LOGGER.info("Endpoint guardar odontologo fue llamado con el request: " + JsonPrinter.toString(odontologoEntradaDto));
        Odontologo odontologo = modelMapper.map(odontologoEntradaDto, Odontologo.class);
        LOGGER.info("Objeto a guardar en la BD: " + JsonPrinter.toString(odontologo));
        //mapeo de entidad a dto
        OdontologoSalidaDto odontologoSalidaDto = modelMapper.map(odontologoRepository.save(odontologo), OdontologoSalidaDto.class);
        LOGGER.info("Data guardada de odontologo: " + JsonPrinter.toString(odontologoSalidaDto));
        return odontologoSalidaDto;
    }
    @Override
    public List<OdontologoSalidaDto> listarOdontologos() {
        //odontologoIDao.listarTodos();
        //mapeo entidad a dto
        List<OdontologoSalidaDto> odontologos = odontologoRepository.findAll()
                .stream()
                .map(odontologo -> modelMapper.map(odontologo, OdontologoSalidaDto.class))
                .toList();
        LOGGER.info("Listado de todos los odontologos: {}", JsonPrinter.toString(odontologos));
        return odontologos;
    }

    @Override
    public OdontologoSalidaDto buscarOdontologoPorId(Long id) {

        Odontologo odontologoBuscado = odontologoRepository.findById(id).orElse(null);
        OdontologoSalidaDto odontologoEncontrado = null;

        if (odontologoBuscado != null){
            odontologoEncontrado = modelMapper.map(odontologoBuscado, OdontologoSalidaDto.class);
            LOGGER.info("Odontologo encontrado: {}", JsonPrinter.toString(odontologoEncontrado));
        } else LOGGER.error("No se ha encontrado el odontologo con id {}", id);

        return odontologoEncontrado;
    }

    @Override
    public OdontologoSalidaDto actualizarOdontologo(OdontologoEntradaDto odontologoEntradaDto, Long id) {
        Odontologo odontologoRecibido = modelMapper.map(odontologoEntradaDto, Odontologo.class);
        Odontologo odontologoAActualizar = odontologoRepository.findById(id).orElse(null);
        OdontologoSalidaDto odontologoSalidaDto = null;

        if(odontologoAActualizar != null){

            odontologoRecibido.setId(odontologoAActualizar.getId());
            odontologoAActualizar = odontologoRecibido;
            
            odontologoRepository.save(odontologoAActualizar);
            odontologoSalidaDto = modelMapper.map(odontologoAActualizar, OdontologoSalidaDto.class);
            LOGGER.warn("Odontologo actualizado: {}", JsonPrinter.toString(odontologoSalidaDto));

        } else {
            LOGGER.error("No fue posible actualizar el odontologo porque no se encuentra en nuestra base de datos");
            //lanzar excepcion
        }

        return odontologoSalidaDto;
    }

    @Override
    public void eliminarOdontologo(Long id) throws ResourceNotFoundException {
        if(buscarOdontologoPorId(id) != null){
            odontologoRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el odontologo con id {}", id);
        }  else {
            throw new ResourceNotFoundException("No existe registro de odontologo con id " + id);
        }

    }
}
