package com.backend.parcial.entity;

public class Odontologo {
    private Long id;
    private int numeroDeMatricula;
    private String nombre;
    private String apellido;

    public Odontologo(Long id, int numeroDeMatricula, String nombre, String apellido) {
        this.id = id;
        this.numeroDeMatricula = numeroDeMatricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Odontologo(int numeroDeMatricula, String nombre, String apellido) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.numeroDeMatricula = numeroDeMatricula;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumeroDeMatricula() {
        return numeroDeMatricula;
    }

    public void setNumeroDeMatricula(int numeroDeMatricula) {
        this.numeroDeMatricula = numeroDeMatricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return "\nId: " + id + " - NumeroDeMatricula: " + numeroDeMatricula + " - Nombre: " + nombre + " - Apellido: " + apellido;
    }
}
