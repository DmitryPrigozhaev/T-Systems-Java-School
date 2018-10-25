package com.railwaycompany.dao.api;

import com.railwaycompany.entities.User;

import java.io.Serializable;

public interface UserDao extends GenericDao<User> {

    User getUserByEmail(String email);

    void deleteUserByEmail(String email);

    void deleteById(Serializable id);

}
