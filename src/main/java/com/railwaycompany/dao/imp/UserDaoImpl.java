package com.railwaycompany.dao.imp;

import com.railwaycompany.dao.api.UserDao;
import com.railwaycompany.entities.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class UserDaoImpl extends AbstractGenericDao<User> implements UserDao {

    private static final Logger LOG = Logger.getLogger(UserDaoImpl.class.getName());

    private static final String FIND_USER_BY_EMAIL = "SELECT u FROM User u WHERE u.email = :email";

    @Override
    public User getUserByEmail(String email) {
        Query queryGetUserByEmail = getCurrentSession().createQuery(FIND_USER_BY_EMAIL);
        queryGetUserByEmail.setParameter("email", email);
        User user = null;
        try {
            user = (User) queryGetUserByEmail.getSingleResult();
        } catch (Exception e) {
            LOG.warn(e);
        }
        return user;
    }

}