/*
 * Copyright (c) 2024 Daniel I. Tikamori. All rights reserved.
 */

package cc.tkmr.service;

import java.util.List;

public interface CrudService<ID, T> {
    List<T> findAll();
    T findById(ID id);
    T create(T entity);
    T update(ID id, T entity);
    void delete(ID id);
}