package com.railwaycompany.services.imp;

import com.railwaycompany.dao.api.UserDao;
import com.railwaycompany.entities.User;
import com.railwaycompany.services.api.UserService;
import com.railwaycompany.services.exceptions.AlreadyRegisteredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(User user) throws AlreadyRegisteredException {
        if (isUserEmailUnique(user.getEmail())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDao.create(user);
        } else {
            String message = "User with email \"" + user.getEmail() + "\" is already exist!";
            throw new AlreadyRegisteredException(message);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public User getUser(long userId) {
        return userDao.read(userId);
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
    public void updateUser(User user) {
        userDao.update(user);
    }

    @Override
    public void deleteUser(User user) {
        userDao.delete(user);
    }

    @Override
    public boolean isUserEmailUnique(String email) {
        User user = userDao.getUserByEmail(email);
        return (user == null);
    }
}