package ru.tsystems.school.dao;

import java.io.Serializable;

public interface GenericDao<T, PK extends Serializable> {

    void create(T entity);

    T read(PK id);

    void update(T entity);

    void delete(PK id);

}