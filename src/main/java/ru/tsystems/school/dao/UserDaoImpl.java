package ru.tsystems.school.dao;

import org.springframework.stereotype.Repository;
import ru.tsystems.school.entities.UserEntity;

@Repository
public class UserDaoImpl extends AbstractGenericDao<UserEntity> implements UserDao {

    @Override
    public UserEntity findByLogin(String login) {
        return getSession().get(UserEntity.class, login);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return getSession().get(UserEntity.class, email);
    }

}