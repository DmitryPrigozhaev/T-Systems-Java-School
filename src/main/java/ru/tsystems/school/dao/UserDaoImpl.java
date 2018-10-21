package ru.tsystems.school.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.tsystems.school.entities.UserEntity;

@Repository
public class UserDaoImpl extends AbstractGenericDao<UserEntity> implements UserDao {

    @Autowired
    UserDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public UserEntity findByLogin(String login) {
        return getCurrentSession().get(UserEntity.class, login);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return getCurrentSession().get(UserEntity.class, email);
    }

}