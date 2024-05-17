package com.backend.parcial.repository.impl;

import com.backend.parcial.entity.Odontologo;
import com.backend.parcial.repository.IDao;
import com.backend.parcial.repository.dbConnection.H2Connection;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OdontologoDaoMemory implements IDao<Odontologo> {
    private final Logger LOGGER = Logger.getLogger(OdontologoDaoMemory.class);

    private List<Odontologo> listaOdontologo =  Arrays.asList(new Odontologo(8L,2558, "Jan", "Perez"), new Odontologo(9L,255, "Andrea", "Parra"));

    public OdontologoDaoMemory() {
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        listaOdontologo.add(odontologo);
        LOGGER.info("odontologo guardado en Memory: " + odontologo);
        return odontologo;
    }

    @Override
    public List<Odontologo> listar() {
        LOGGER.info("Listado de odontologos obtenido desde Memory: " + listaOdontologo);
        return listaOdontologo;
    }
}
