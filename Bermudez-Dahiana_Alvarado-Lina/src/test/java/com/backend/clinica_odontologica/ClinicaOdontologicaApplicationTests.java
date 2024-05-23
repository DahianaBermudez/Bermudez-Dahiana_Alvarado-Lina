package com.backend.clinica_odontologica;

import com.backend.clinica_odontologica.repository.impl.OdontologoDaoH2;
import com.backend.clinica_odontologica.repository.impl.OdontologoDaoMemory;
import com.backend.clinica_odontologica.service.impl.OdontologoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class ClinicaOdontologicaApplicationTests {

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
