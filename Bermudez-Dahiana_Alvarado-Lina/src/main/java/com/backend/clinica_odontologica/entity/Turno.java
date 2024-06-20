package com.backend.clinica_odontologica.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TURNOS")
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTurno;
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "odontologo_id" , nullable = false)
    private Odontologo odontologo;
    private LocalDateTime fechaHora;

    public Turno() {
    }

    public Turno(Long idTurno, Paciente paciente, Odontologo odontologo, LocalDateTime fechaHora) {
        this.idTurno = idTurno;
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fechaHora = fechaHora;
    }

    public Long getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(Long idTurno) {
        this.idTurno = idTurno;
    }

    public Paciente getPaciente() {
        return paciente;
    }
    public String getPacienteNombre() {
        return paciente.getNombre();
    }
    public String getOdontologoNombre() {
        return odontologo.getNombre();
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

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
}
