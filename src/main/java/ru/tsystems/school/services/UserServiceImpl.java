package ru.tsystems.school.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.school.dao.UserDao;
import ru.tsystems.school.entities.User;

import java.io.Serializable;
import java.util.List;

@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public List<User> getAll() {
        return userDao.getAll();
    }

    public User getByLogin(String login) {
        return userDao.getByLogin(login);
    }

    public User getByEmail(String email) {
        return userDao.getByEmail(email);
    }

    @Transactional(readOnly = false)
    public Serializable save(User entity) {
        return userDao.save(entity);
    }

    @Transactional(readOnly = false)
    public void saveOrUpdate(User entity) {
        userDao.saveOrUpdate(entity);
    }

    @Transactional(readOnly = false)
    public void delete(User entity) {
        userDao.delete(entity);
    }

    @Transactional(readOnly = false)
    public void deleteById(Serializable id) {
        userDao.deleteById(id);
    }
    @Transactional(readOnly = false)
    public void deleteAll() {
        userDao.deleteAll();
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