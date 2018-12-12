package com.railwaycompany.dao.api;

import com.railwaycompany.entities.User;

/**
 * Interface for Data Access Object of {@link User}
 *
 * @author Dmitry Prigozhaev
 * @version 1.0
 */

public interface UserDao extends GenericDao<User> {

    User getUserByEmail(String email);

}