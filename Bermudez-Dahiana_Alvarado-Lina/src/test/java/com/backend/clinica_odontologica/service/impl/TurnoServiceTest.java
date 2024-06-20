package com.backend.clinica_odontologica.service.impl;

import com.backend.clinica_odontologica.dto.entrada.DomicilioEntradaDto;
import com.backend.clinica_odontologica.dto.entrada.OdontologoEntradaDto;
import com.backend.clinica_odontologica.dto.entrada.PacienteEntradaDto;
import com.backend.clinica_odontologica.dto.entrada.TurnoEntradaDto;
import com.backend.clinica_odontologica.dto.salida.TurnoSalidaDto;
import com.backend.clinica_odontologica.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@RunWith(SpringJUnit4ClassRunner.class)
class TurnoServiceTest {

    @Autowired
    private PacienteService pacienteService;


    @Autowired
    private OdontologoService odontologoService;

    @Autowired
    private TurnoService turnoService;


    @Test
    @Order(1)
    void deberiaRegistrarseUnTurnoConFecha20241202T1500_yRetornarSuId() throws ResourceNotFoundException {
        PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("Juan", "Perez", 123456, LocalDate.of(2024, 6, 22), new DomicilioEntradaDto("Calle", 123, "Localidad", "Provincia"));

        pacienteService.registrarPaciente(pacienteEntradaDto);
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto(123456,"ramiro", "lopez");

        odontologoService.guardarOdontologo(odontologoEntradaDto);
        TurnoEntradaDto turnoEntradaDto = new TurnoEntradaDto(1L, 1L, LocalDateTime.of(2024, 12, 02, 15,00));

        TurnoSalidaDto turnoSalidaDto = turnoService.guardarTurno(turnoEntradaDto);

        //assert
        assertNotNull(turnoSalidaDto);
        assertNotNull(turnoSalidaDto.getId());
        assertEquals("2024-12-02T15:00", turnoSalidaDto.getFechaHora().toString());
    }


    @Test
    @Order(2)
    void deberiaDevolverUnaListaNoVaciaDeTurnos(){
        List<TurnoSalidaDto> listadoDeTurnos = turnoService.listarTurnos();
        assertFalse(listadoDeTurnos.isEmpty());
    }

    @Test
    @Order(3)
    void deberiaEliminarseElTurnoConId1(){

        assertDoesNotThrow(() -> turnoService.eliminarTurno(1L));
    }

    @Test
    @Order(4)
    void deberiaDevolverUnaListaVaciaDeTurnos(){
        List<TurnoSalidaDto> listadoDeTurnos = turnoService.listarTurnos();
        assertTrue(listadoDeTurnos.isEmpty());
    }

}

