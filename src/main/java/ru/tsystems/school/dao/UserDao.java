package ru.tsystems.school.dao;

import ru.tsystems.school.entities.UserEntity;


public interface UserDao extends GenericDao<UserEntity> {

    UserEntity findByLogin(String login);

    UserEntity findByEmail(String email);

}
