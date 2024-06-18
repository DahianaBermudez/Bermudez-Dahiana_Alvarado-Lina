package com.backend.clinica_odontologica;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.backend.clinica_odontologica.dbConnection.H2Connection;

@SpringBootApplication
public class ClinicaOdontologicaApplication {
	private static Logger LOGGER = LoggerFactory.getLogger(ClinicaOdontologicaApplication.class);
	public static void main(String[] args) {

		H2Connection.crearTablas();
		SpringApplication.run(ClinicaOdontologicaApplication.class, args);

		LOGGER.info("clinicaOdontologica is now running...");
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
}
