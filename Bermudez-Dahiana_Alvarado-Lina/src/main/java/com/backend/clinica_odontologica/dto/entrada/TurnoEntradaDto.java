package com.backend.clinica_odontologica.dto.entrada;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

public class TurnoEntradaDto {
    @Positive(message = "El id del paciente no puede ser nulo o menor a cero")
    private Long idPaciente;
    @Positive(message = "El id del odontolog no puede ser nulo o menor a cero")
    private Long idOdontologo;
    @FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy")
    @NotNull(message = "Debe especificarse la fecha del turno")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime fechaHora;

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public void setIdOdontologo(Long idOdontologo) {
        this.idOdontologo = idOdontologo;
    }

    public TurnoEntradaDto(){

    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public Long getIdOdontologo() {
        return idOdontologo;
    }

    public TurnoEntradaDto(Long idPaciente, Long idOdontologo, LocalDateTime fechaHora) {
        this.idPaciente = idPaciente;
        this.idOdontologo = idOdontologo;
        this.fechaHora = fechaHora;
    }

}
