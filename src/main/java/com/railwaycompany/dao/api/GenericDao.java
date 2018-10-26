package com.railwaycompany.dao.api;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<E extends Serializable> {

    void create(E entity);

    E read(int key);

    List<E> readAll();

    void update(E entity);

    void delete(E entity);

    void deleteAll();
}
