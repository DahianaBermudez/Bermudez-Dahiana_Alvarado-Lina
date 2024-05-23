package com.backend.clinica_odontologica.repository;

import java.util.List;

public interface IDao<T> {
    T guardar(T t);
     List<T> listar();
}
