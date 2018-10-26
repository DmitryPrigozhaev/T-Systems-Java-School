package com.railwaycompany.dao.imp;

import com.railwaycompany.dao.api.UserDao;
import com.railwaycompany.entities.User;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl extends AbstractGenericDao<User> implements UserDao {

    private static final String FIND_USER_BY_LOGIN = "SELECT u FROM User u WHERE u.email = :email";

    @Override
    public User getUserByEmail(String email) {
        Query queryGetUserByEmail = getCurrentSession().createQuery(FIND_USER_BY_LOGIN);
        queryGetUserByEmail.setParameter("email", email);
        User user = null;
        try {
            user = (User) queryGetUserByEmail.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void deleteUserByEmail(String email) {
        User user = getUserByEmail(email);
        delete(user);
    }

}