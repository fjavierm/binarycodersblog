package com.wordpress.binarycoders.calculator.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, ID extends Serializable> {
    void persist(T entity);
    void update(T entity);
    void delete(T entity);
    void delete(ID id);
    T findById(ID id);
    List<T> findAll();
    Long countAll();
}
