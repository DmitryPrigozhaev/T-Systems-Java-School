package ru.tsystems.school.dao;

import org.springframework.stereotype.Repository;
import ru.tsystems.school.entities.User;

import java.io.Serializable;

@Repository
public class UserDaoImpl extends AbstractGenericDao<User> implements UserDao {

    // TODO переделать через аннотацию
    @Override
    public User getByLogin(String login) {
        return getCurrentSession().get(User.class, login);
    }

    // TODO переделать через аннотацию
    @Override
    public User getByEmail(String email) {
        return getCurrentSession().get(User.class, email);
    }

    @Override
    public void deleteById(Serializable id) {
        User user = (User) getCurrentSession().load(User.class, id);
        if (null != user) {
            getCurrentSession().delete(user);
        }
    }

}