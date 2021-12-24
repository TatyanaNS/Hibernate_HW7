package com.goit.dao;

import java.text.ParseException;
import java.util.*;

public interface Dao<T> {
    List<T> getAll() throws ParseException;
    Optional<T> get(Long id);
    void create(T entity);
    void update(T entity) throws ParseException;
    void delete(T entity);
}
