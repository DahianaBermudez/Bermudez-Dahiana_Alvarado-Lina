package com.backend.parcial.test;
import com.backend.parcial.repository.impl.OdontologoDaoH2;
import com.backend.parcial.repository.impl.OdontologoDaoMemory;
import com.backend.parcial.service.impl.OdontologoService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OdontologoServiceTest {
    private OdontologoService odontologoService;

    @Test
    void deberiaRetonarUnaListaNoVaciaDeOdontologoEnH2(){

        odontologoService = new OdontologoService(new OdontologoDaoH2());
        assertFalse(odontologoService.listaOdontologos().isEmpty());

    }
    @Test
    void deberiaRetonarUnaListaNoVaciaDeOdontologoEnMemoria(){

        odontologoService = new OdontologoService(new OdontologoDaoMemory());
        assertFalse(odontologoService.listaOdontologos().isEmpty());

    }

}


