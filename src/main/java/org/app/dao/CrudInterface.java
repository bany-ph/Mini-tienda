package org.app.dao;

import java.util.List;

public interface CrudInterface<T> {
    void create(T object);
    List<T> findAll();
    boolean update(T object);
    boolean delete(T object);
}
