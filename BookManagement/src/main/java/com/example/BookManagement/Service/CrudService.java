package com.example.BookManagement.Service;

import java.util.List;
import java.util.Optional;

public interface CrudService<T> {
    //Al utilizar interfaz estamos aumentando el acoplamiento, pensando en escalabilidad y que a futuro se agregaran mas clases que implementen estos servicios
    //para estandarizar la estructura de metodos, Entonces se penso en utilizar una interfaz y no tratar el proyecto como un caso aislado.
    Optional<T> findById(String id);
    List<T> findAll( final Integer count);
    void deleteById(String id);
    Optional<T> updateEach(String id);
}
