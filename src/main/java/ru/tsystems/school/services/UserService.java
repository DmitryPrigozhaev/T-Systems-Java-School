package ru.tsystems.school.services;

import ru.tsystems.school.dao.UserDao;
import ru.tsystems.school.dao.UserDaoImpl;
import ru.tsystems.school.entities.User;

import java.util.List;

public class UserService {

    private UserDao userDao = new UserDaoImpl();

    public void createUser(User user) {
        userDao.create(user);
    }

    public User readUser(int id) {
        return userDao.read(id);
    }

    public void updateUser(User user) {
        userDao.update(user);
    }

    public void deleteUser(User user) {
        userDao.delete(user);
    }

    public List<User> readAllUser() {
        return userDao.readAll();
    }

}
