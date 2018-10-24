package ru.tsystems.school.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Repository
public abstract class AbstractGenericDao<E> implements GenericDao<E> {

    private final Class<E> entityClass;

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    AbstractGenericDao() {
        this.entityClass = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    // извлекает текущий сеанс из фабрики SessionFactory
    Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public E getById(Serializable id) {
        return (E) getCurrentSession().get(this.entityClass, id);
    }

    @Override
    public Serializable save(E entity) {
        return getCurrentSession().save(entity);
    }

    @Override
    public void saveOrUpdate(E entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public void delete(E entity) {
        getCurrentSession().delete(entity);
    }

    @Override
    public void deleteAll() {
        List<E> entities = getAll();
        for (E entity : entities) {
            getCurrentSession().delete(entity);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> getAll() {
        return getCurrentSession().createCriteria(this.entityClass).list();
    }

    @Override
    public Criteria createEntityCriteria() {
        return getCurrentSession().createCriteria(entityClass);
    }
}