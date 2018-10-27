package com.railwaycompany.services.api;

import com.railwaycompany.entities.User;

import java.util.List;

public interface UserService {

    void addUser(User entity);

    User getUser(int id);

    User getUserByEmail(String email);

    List<User> getAllUsers();

    void updateUser(User entity);

    void deleteUser(User entity);

    void deleteUserByEmail(String email);

    void deleteAllUsers();

    boolean isUserEmailUnique(String email);
}