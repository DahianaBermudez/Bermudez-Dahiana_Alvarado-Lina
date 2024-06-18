package com.backend.clinica_odontologica.dto.salida;

import com.backend.clinica_odontologica.entity.Odontologo;
import com.backend.clinica_odontologica.entity.Paciente;

import java.time.LocalDateTime;

public class TurnoSalidaDto {
    private Long id;

    private PacienteSalidaDto paciente;
    private OdontologoSalidaDto odontologo;
    private LocalDateTime fechaHora;

    public TurnoSalidaDto(){

    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public TurnoSalidaDto(Long id, PacienteSalidaDto paciente, OdontologoSalidaDto odontologo, LocalDateTime fechaHora) {
        this.id = id;
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fechaHora = fechaHora;
    }

    public PacienteSalidaDto getPaciente() {
        return paciente;
    }

    public OdontologoSalidaDto getOdontologo() {
        return odontologo;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setPaciente(PacienteSalidaDto paciente) {
        this.paciente = paciente;
    }

    public void setOdontologo(OdontologoSalidaDto odontologo) {
        this.odontologo = odontologo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
