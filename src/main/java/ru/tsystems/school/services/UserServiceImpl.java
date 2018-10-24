package ru.tsystems.school.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.school.dao.UserDao;
import ru.tsystems.school.entities.User;

import java.io.Serializable;
import java.util.List;

@Service("userService")
@Transactional(readOnly = false)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional(readOnly = true)
    @Override
    public User getUserById(Serializable id) {
        return userDao.getById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    @Override
    public Serializable saveUser(User entity) {
        return userDao.save(entity);
    }

    @Override
    public void saveOrUpdateUser(User entity) {
        userDao.saveOrUpdate(entity);
    }

    @Override
    public void deleteUser(User entity) {
        userDao.delete(entity);
    }

    @Override
    public void deleteUserById(Serializable id) {
        userDao.deleteById(id);
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
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public boolean isUserEmailUnique(Long id, String email) {
        User user = getUserByEmail(email);
        return (user == null || ((id != null) && (user.getId() == id)));
    }
}