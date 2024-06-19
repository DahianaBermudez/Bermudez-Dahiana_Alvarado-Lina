package com.backend.clinica_odontologica.service.impl;


import com.backend.clinica_odontologica.dto.entrada.OdontologoEntradaDto;
import com.backend.clinica_odontologica.dto.salida.OdontologoSalidaDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OdontologoServiceTest {
    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    void deberiaRegistrarseUnOdontologoYRetornarSuId(){

        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto(11223344, "María", "Lorenza");

        OdontologoSalidaDto odontologoSalidaDto = odontologoService.guardarOdontologo(odontologoEntradaDto);

        //assert
        assertNotNull(odontologoSalidaDto);
        assertNotNull(odontologoSalidaDto.getId());
        assertEquals("María", odontologoSalidaDto.getNombre());
    }

    @Test
    @Order(2)
    void deberiaDevolverUnaListaDeOdontologos(){
        List<OdontologoSalidaDto> listadoOdontologos = odontologoService.listarOdontologos();
        assertFalse(listadoOdontologos.isEmpty());

    }

    @Test
    @Order(3)
    void deberiaEliminarseElOdontologoConId(){
        assertDoesNotThrow(() -> odontologoService.eliminarOdontologo(1L));
    }

    @Test
    @Order(4)
    void deberiaDevolverUnaListaVaciaDeOdontologos(){
        List<OdontologoSalidaDto> listadoOdontologos = odontologoService.listarOdontologos();
        assertTrue(listadoOdontologos.isEmpty());
    }
}
