package ru.tsystems.school.dao;

import ru.tsystems.school.entities.User;

import java.util.List;

public interface UserDao {

    void create(User user);

    User read(int id);

    void update(User user);

    void delete(User user);

    List<User> readAll();
}