package ru.tsystems.school.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.tsystems.school.entities.User;

import java.io.Serializable;

@Repository("userDao")
public class UserDaoImpl extends AbstractGenericDao<User> implements UserDao {

    @Override
    public User getUserByEmail(String email) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("EMAIL", email));
        return (User) criteria.uniqueResult();
    }

    @Override
    public void deleteUserByEmail(String email) {
        User user = getUserByEmail(email);
        delete(user);
    }

    @Override
    public void deleteById(Serializable id) {
        User user = (User) getCurrentSession().load(User.class, id);
        if (null != user) {
            getCurrentSession().delete(user);
        }
    }
}