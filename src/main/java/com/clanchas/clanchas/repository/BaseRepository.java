package com.clanchas.clanchas.repository;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T> {
    /**
     * Crea un nuevo registro
     *
     * @param obj Objeto a insertar
     * @return regresa si se guardó el objeto
     */
    Optional<T> save(T obj);

    /**
     * Encuentra todos los registros de la base de datos
     *
     * @return Una lista con los registros de la base de datos del objeto
     */
    List<T> findAll();

    /**
     * Busca en la base de datos el registro con el id mandado
     *
     * @param id id del objeto a buscar
     * @return El objeto encontrado, si no lo encuentra regresa nulo
     */
    Optional<T> findById(Long id);

    /**
     * Elimina un registro
     *
     * @param id id del registro a eliminar
     */
    void deleteById(Long id);

    /**
     * Actualiza el objeto mandado
     *
     * @param obj El objeto a actualizar
     * @return regresa el registro actualizado
     */
    Optional<T> update(T obj);
}
