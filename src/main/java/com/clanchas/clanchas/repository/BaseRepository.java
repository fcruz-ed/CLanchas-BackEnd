package com.clanchas.clanchas.repository;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T> {
    /**
     * Crea un nuevo registro.
     * @param obj Objeto a insertar.
     * @return regresa si se guardó el objeto..
     */
    Optional<T> save(T obj);

    /**
     * Encuentra todos los registros de la base de datos
     * @return Una lista con los registros de la base de datos del objeto.
     */
    List<T> findAll();

    /**
     *
     * @param id id del objeto a buscar.
     * @return El objeto encontrado, si no lo encuentra regresa nulo.
     */
    Optional<T> findById(Long id);

    /**
     *
     * @param id id del registro a eliminar.
     * @return regresa si se eliminó el registro.
     */
    void deleteById(Long id);

    /**
     *
     * @param obj El objeto a actualizar.
     * @return regresa si se actualizó el registro.
     */
    void update(T obj);
}
