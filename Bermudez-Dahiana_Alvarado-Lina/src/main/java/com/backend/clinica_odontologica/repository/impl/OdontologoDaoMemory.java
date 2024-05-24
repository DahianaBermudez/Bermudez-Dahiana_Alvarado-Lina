package com.backend.clinica_odontologica.repository.impl;

import com.backend.clinica_odontologica.entity.Odontologo;
import com.backend.clinica_odontologica.repository.IDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class OdontologoDaoMemory implements IDao<Odontologo> {
    private final Logger LOGGER = LoggerFactory.getLogger(OdontologoDaoMemory.class);

    private List<Odontologo> listaOdontologo =  Arrays.asList(new Odontologo(8L,2558, "Jan", "Perez"), new Odontologo(9L,255, "Andrea", "Parra"));

    public OdontologoDaoMemory() {
    }

    @Override
    public Odontologo registrar(Odontologo odontologo) {
        listaOdontologo.add(odontologo);
        LOGGER.info("odontologo guardado en Memory: " + odontologo);
        return odontologo;
    }

    @Override
    public List<Odontologo> listarTodos() {
        LOGGER.info("Listado de odontologos obtenido desde Memory: " + listaOdontologo);
        return listaOdontologo;
    }
    @Override
    public Odontologo buscarPorId(Long id) {
        return null;
    }

}
