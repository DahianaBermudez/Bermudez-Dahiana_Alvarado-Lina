package com.backend.parcial.repository.impl;
import com.backend.parcial.entity.Odontologo;
import com.backend.parcial.repository.IDao;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoMemory implements IDao<Odontologo> {
    private final Logger LOGGER = Logger.getLogger(OdontologoDaoMemory.class);

    private List<Odontologo> listaOdontologo = new ArrayList<>();
    private static long ultimoId = 0;

    public OdontologoDaoMemory() {

        listaOdontologo.add(new Odontologo(++ultimoId, 2558, "Jan", "Perez"));
        listaOdontologo.add(new Odontologo(++ultimoId, 255, "Andrea", "Parra"));
        listaOdontologo.add(new Odontologo(++ultimoId, 2566, "Andres", "Benitez"));

    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        odontologo.setId(++ultimoId);
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
