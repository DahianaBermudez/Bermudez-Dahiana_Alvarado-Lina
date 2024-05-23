package com.backend.clinica_odontologica.service;

import com.backend.clinica_odontologica.entity.Odontologo;

import java.util.List;

public interface IOdontologoService {
    Odontologo guardarOdontologo(Odontologo odontologo);
    List<Odontologo> listaOdontologos();
}
