package com.backend.clinica_odontologica.service;

import com.backend.clinica_odontologica.dto.entrada.OdontologoEntradaDto;
import com.backend.clinica_odontologica.dto.salida.OdontologoSalidaDto;
import com.backend.clinica_odontologica.entity.Odontologo;

import java.util.List;

public interface IOdontologoService {
    Odontologo guardarOdontologo(OdontologoEntradaDto odontologoEntradaDto);
    List<OdontologoSalidaDto> listaOdontologos();
}
