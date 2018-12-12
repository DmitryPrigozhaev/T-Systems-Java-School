package com.railwaycompany.dao.api;

import java.io.Serializable;
import java.util.List;

/**
 * Generic interface of Data Access Object
 *
 * @author Dmitry Prigozhaev
 * @version 1.0
 */

public interface GenericDao<E extends Serializable> {

    void create(E entity);

    E read(long key);

    List<E> readAll();

    void update(E entity);

    void delete(E entity);

    void deleteAll();
}
