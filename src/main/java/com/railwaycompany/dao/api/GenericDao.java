package com.railwaycompany.dao.api;

import org.hibernate.Criteria;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<E> {

    E getById(Serializable id);

    Serializable save(E entity);

    void saveOrUpdate(E entity);

    void delete(E entity);

    void deleteById(Serializable id);

    void deleteAll();

    List<E> getAll();

    Criteria createEntityCriteria();
}
