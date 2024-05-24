package com.backend.clinica_odontologica.entity;

import java.util.Date;

public class Turno {
    private Long id;
    private Paciente paciente;
    private Odontologo odontologo;
    private Date fechaYhora;

    public Turno(Long id, Paciente paciente, Odontologo odontologo, Date fechaYhora) {
        this.id = id;
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fechaYhora = fechaYhora;
    }

    public Turno(Paciente paciente, Odontologo odontologo, Date fechaYhora) {
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fechaYhora = fechaYhora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public Date getFechaYhora() {
        return fechaYhora;
    }

    public void setFechaYhora(Date fechaYhora) {
        this.fechaYhora = fechaYhora;
    }
}
