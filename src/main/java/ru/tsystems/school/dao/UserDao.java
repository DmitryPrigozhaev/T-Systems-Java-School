package ru.tsystems.school.dao;

import ru.tsystems.school.entities.User;


public interface UserDao extends GenericDao<User> {

    User getByLogin(String login);

    User getByEmail(String email);

}
