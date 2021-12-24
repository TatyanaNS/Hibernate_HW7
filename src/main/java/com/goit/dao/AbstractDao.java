package com.goit.dao;

import com.goit.config.PersistenceProvider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.text.ParseException;
import javax.persistence.*;
import java.lang.reflect.*;
import java.util.*;

public abstract class AbstractDao<T> implements Dao<T> {

    private final Class<T> entityType;
    protected EntityManager em = PersistenceProvider.getEntityManager();

    public AbstractDao(Class<T> entityType) {
        this.entityType = entityType;
    }

    public AbstractDao() {
        Type daoType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) daoType).getActualTypeArguments();
        this.entityType = ((Class<T>) params[0]);
    }

    @Override
    public void create(T entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }

    @Override
    public void update(T entity) {
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
    }

    @Override
    public void delete(T entity) {
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
    }

    @Override
    public List<T> getAll() {
        TypedQuery<T> getAll = em.createQuery("from " + entityType.getSimpleName(), entityType);
        return getAll.getResultList();
    }

    @Override
    public Optional<T> get(Long id) {
        T entity = em.find(entityType, id);
        return Optional.of(entity);
    }
}