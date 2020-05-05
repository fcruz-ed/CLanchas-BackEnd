package com.clanchas.clanchas.service;

import java.util.List;

public interface BaseService<T> {

    T save(T obj);

    List<T> findAll();

    T findById(Long id);

    T update(T obj);

    void delete(Long id);
}
