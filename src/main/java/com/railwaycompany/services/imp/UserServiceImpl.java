package com.railwaycompany.services.imp;

import com.railwaycompany.dao.api.UserDao;
import com.railwaycompany.entities.User;
import com.railwaycompany.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional(readOnly = false)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void addUser(User entity) {
        if (isUserEmailUnique(entity.getEmail())) {
            userDao.create(entity);
        } else {
            String message = "User with email \"" + entity.getEmail() + "\" is already exist!";
            //TODO здесь бросить исключение
        }
    }

    @Transactional(readOnly = true)
    @Override
    public User getUser(int id) {
        return userDao.read(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        return userDao.readAll();
    }

    @Override
    public void updateUser(User entity) {
        userDao.update(entity);
    }

    @Override
    public void deleteUser(User entity) {
        userDao.delete(entity);
    }

    @Override
    public void deleteUserByEmail(String email) {
        userDao.deleteUserByEmail(email);
    }

    @Override
    public void deleteAllUsers() {
        userDao.deleteAll();
    }

    @Override
    public boolean isUserEmailUnique(String email) {
        User user = getUserByEmail(email);
        return (user == null);
    }
}