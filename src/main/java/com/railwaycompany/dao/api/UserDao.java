package com.railwaycompany.dao.api;

import com.railwaycompany.entities.User;

public interface UserDao extends GenericDao<User> {

    User getUserByEmail(String email);

    void deleteUserByEmail(String email);

}