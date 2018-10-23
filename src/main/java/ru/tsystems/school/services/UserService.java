package ru.tsystems.school.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.school.dao.UserDao;
import ru.tsystems.school.entities.User;

import java.io.Serializable;
import java.util.List;

@Service("userService")
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    public User getByLogin(String login) {
        return userDao.getByLogin(login);
    }

    public User getByEmail(String email) {
        return userDao.getByEmail(email);
    }

    public Serializable save(User entity) {
        return userDao.save(entity);
    }

    public void saveOrUpdate(User entity) {
        userDao.saveOrUpdate(entity);
    }

    public void delete(User entity) {
        userDao.delete(entity);
    }

    public void deleteAll() {
        userDao.deleteAll();
    }

    public List<User> getAll() {
        return userDao.getAll();
    }

    public User getById(Serializable id) {
        return userDao.getById(id);
    }

    public List<User> getAllByExample(User entity) {
        return userDao.getAllByExample(entity);
    }

    public void clear() {
        userDao.clear();
    }

    public void flush() {
        userDao.flush();
    }
}