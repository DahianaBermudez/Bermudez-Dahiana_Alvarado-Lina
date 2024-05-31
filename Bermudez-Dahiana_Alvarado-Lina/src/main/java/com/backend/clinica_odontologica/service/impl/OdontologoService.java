package com.backend.clinica_odontologica.service.impl;

import com.backend.clinica_odontologica.dto.entrada.OdontologoEntradaDto;
import com.backend.clinica_odontologica.dto.salida.OdontologoSalidaDto;
import com.backend.clinica_odontologica.entity.Odontologo;
import com.backend.clinica_odontologica.repository.IDao;
import com.backend.clinica_odontologica.service.IOdontologoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {
    private IDao<Odontologo> odontologoIDao;

    public OdontologoService(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }
    public OdontologoService() {

    }

    @Override
    public Odontologo guardarOdontologo(OdontologoEntradaDto odontologoEntradaDto) {
        //Logica de negocio
        //Mapeo de dtos a entidad
        //odontologoIDao.registrar(odontologo);
        // mapeo entidad dto
        return null;
    }
    @Override
    public List<OdontologoSalidaDto> listaOdontologos() {
        //odontologoIDao.listarTodos();
        //mapeo entidad a dto
        return null;
    }
}
