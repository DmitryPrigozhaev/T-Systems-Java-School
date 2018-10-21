package ru.tsystems.school.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Repository
@SuppressWarnings("unchecked")
public abstract class AbstractGenericDao<E> implements GenericDao<E> {

    private final Class<E> entityClass;
    private SessionFactory sessionFactory;

    @Autowired
    AbstractGenericDao(SessionFactory sessionFactory) {
        this.entityClass = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.sessionFactory = sessionFactory;
    }

    // извлекает текущий сеанс из фабрики SessionFactory
    Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
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

    @Override
    public List<E> getAll() {
        return getCurrentSession().createCriteria(this.entityClass).list();
    }

    //TODO разобраться с методом
    @Override
    public List<E> getAllByExample(E entity) {
        Example example = Example.create(entity).ignoreCase().enableLike().excludeZeroes();
        return getCurrentSession().createCriteria(this.entityClass).add(example).list();
    }

    @Override
    public E getById(Serializable id) {
        return (E) getCurrentSession().get(this.entityClass, id);
    }

    @Override
    public void clear() {
        getCurrentSession().clear();
    }

    @Override
    public void flush() {
        getCurrentSession().flush();
    }
}