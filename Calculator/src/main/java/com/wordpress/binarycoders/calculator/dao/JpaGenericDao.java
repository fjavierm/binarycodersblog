package com.wordpress.binarycoders.calculator.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public abstract class JpaGenericDao<T, ID extends Serializable> implements GenericDao<T, ID> {
    
    private static final String FIND_ALL = "select E from %s E";
    private static final String COUNT_ALL = "select count(E) from %s E";
    
    @PersistenceContext
    protected EntityManager em;
    
    private final Class<T> persistentClass;
 
    @SuppressWarnings("unchecked")
    public JpaGenericDao() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }
 
    public Class<T> getPersistentClass() {
        return persistentClass;
    }
 
    @Override
    public T findById(ID id) {
        return em.find(getPersistentClass(), id);
    }
 
    @Override
    public List<T> findAll() {
        
        TypedQuery<T> query = em.createQuery(String.format(FIND_ALL, persistentClass.getName()), persistentClass);
        
        return query.getResultList();
    }
 
    @Override
    public Long countAll() {
 
        TypedQuery<Long> query = em.createQuery(String.format(COUNT_ALL, persistentClass.getName()), Long.class);
        
        return query.getSingleResult();
    }
 
    @Override
    public void persist(T entity) {
        em.persist(entity);
    }
 
    @Override
    public void update(T entity) {
        em.merge(entity);
    }
 
    @Override
    public void delete(T entity) {
        em.remove(entity);
    }
    
    @Override
    public void delete(ID id) {
        em.remove(this.findById(id));
    }
    
}
