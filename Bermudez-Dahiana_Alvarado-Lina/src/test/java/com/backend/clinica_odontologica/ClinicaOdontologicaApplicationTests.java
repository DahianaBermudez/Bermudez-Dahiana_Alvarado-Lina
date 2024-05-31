package com.backend.clinica_odontologica;

import com.backend.clinica_odontologica.dto.entrada.PacienteEntradaDto;
import com.backend.clinica_odontologica.entity.Domicilio;
import com.backend.clinica_odontologica.entity.Paciente;
import com.backend.clinica_odontologica.repository.impl.OdontologoDaoH2;
import com.backend.clinica_odontologica.repository.impl.OdontologoDaoMemory;
import com.backend.clinica_odontologica.repository.impl.PacienteDaoH2;
import com.backend.clinica_odontologica.service.impl.OdontologoService;
import com.backend.clinica_odontologica.service.impl.PacienteService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClinicaOdontologicaApplicationTests {

	private OdontologoService odontologoService;
	private PacienteService pacienteService;

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


//	@Test
//	void deberiaRegistrarseUnPacienteYObtenerElIdCorrespondienteParaPacienteYDomicilioEnH2(){
//
//		pacienteService = new PacienteService(new PacienteDaoH2());
//		PacienteEntradaDto paciente = new Paciente("Nombre", "Apellido", 123456, LocalDate.of(2023, 05, 02), new Domicilio("Calle", 13, "Localidad", "Provincia"));
//
//		Paciente pacienteRegistrado = pacienteService.registrarPaciente(paciente);
//
//		assertNotNull(pacienteRegistrado.getDomicilio().getId());
//		assertNotNull(pacienteRegistrado.getId());
//
//	}


	@Test
	void deberiaRetornarseUnaListaNoVaciaDePacientesEnH2(){
		pacienteService = new PacienteService(new PacienteDaoH2());
		assertFalse(pacienteService.listarPacientes().isEmpty());
	}
}
