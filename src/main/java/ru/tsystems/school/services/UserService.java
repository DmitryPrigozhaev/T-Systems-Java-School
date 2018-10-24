package ru.tsystems.school.services;

import ru.tsystems.school.entities.User;

import java.io.Serializable;
import java.util.List;

public interface UserService {

    User getUserById(Serializable id);

    User getUserByEmail(String email);

    List<User> getAllUsers();

    Serializable saveUser(User entity);

    void saveOrUpdateUser(User entity);

    void deleteUser(User entity);

    void deleteUserById(Serializable id);

    void deleteUserByEmail(String email);

    void deleteAllUsers();

    boolean isUserEmailUnique(Long id, String email);
}