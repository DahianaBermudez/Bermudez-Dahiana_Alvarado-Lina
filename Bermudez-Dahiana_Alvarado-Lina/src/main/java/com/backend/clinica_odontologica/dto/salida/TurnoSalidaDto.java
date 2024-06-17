package com.backend.clinica_odontologica.dto.salida;

import java.time.LocalDateTime;

public class TurnoSalidaDto {
    private Long id;

    private String paciente;
    private String odontologo;
    private LocalDateTime fechaHora;

    public TurnoSalidaDto(){

    }

    public TurnoSalidaDto(Long id, String paciente, String odontologo, LocalDateTime fechaHora) {
        this.id = id;
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fechaHora = fechaHora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
