package ru.tsystems.school.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<E> {

    Serializable save(E entity);

    void saveOrUpdate(E entity);

    void delete(E entity);

    void deleteAll();

    List<E> getAll();

    E getById(Serializable id);

    List<E> getAllByExample(E entity);

    void clear();

    void flush();
}
