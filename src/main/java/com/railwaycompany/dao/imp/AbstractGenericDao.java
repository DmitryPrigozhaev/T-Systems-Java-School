package com.railwaycompany.dao.imp;

import com.railwaycompany.dao.api.GenericDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Repository
public abstract class AbstractGenericDao<E extends Serializable> implements GenericDao<E> {

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
    public void create(E entity) {
        getCurrentSession().persist(entity);
    }

    @Override
    public E read(long id) {
        return getCurrentSession().find(entityClass, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> readAll() {
        CriteriaQuery<E> query = getCurrentSession().getCriteriaBuilder().createQuery(this.entityClass);
        query.from(this.entityClass);
        return getCurrentSession().createQuery(query).getResultList();
    }

    @Override
    public void update(E entity) {
        getCurrentSession().update(entity);
    }

    @Override
    public void delete(E entity) {
        getCurrentSession().delete(entity);
    }

        @Override
    public void deleteAll() {
        List<E> entities = readAll();
        for (E entity : entities) {
            getCurrentSession().delete(entity);
        }
    }

}