package ru.tsystems.school.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.school.dao.UserDao;
import ru.tsystems.school.dao.UserDaoImpl;
import ru.tsystems.school.entities.UserEntity;

import java.io.Serializable;
import java.util.List;

@Service("userService")
@Transactional(readOnly = true)
public class UserService {

    private UserDao userDao;

    @Autowired
    public UserService(UserDaoImpl userDaoImpl) {
        this.userDao = userDaoImpl;
    }

    public List<UserEntity> getAllUsers() {
        return userDao.getAll();
    }

    public UserEntity findByLogin(String login) {
        return userDao.findByLogin(login);
    }

    public UserEntity findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    public Serializable save(UserEntity entity) {
        return userDao.save(entity);
    }

    public void saveOrUpdate(UserEntity entity) {
        userDao.saveOrUpdate(entity);
    }

    public void delete(UserEntity entity) {
        userDao.delete(entity);
    }

    public void deleteAll() {
        userDao.deleteAll();
    }

    public List<UserEntity> findAll() {
        return userDao.getAll();
    }

    public UserEntity findById(Serializable id) {
        return userDao.getById(id);
    }

    public List<UserEntity> findAllByExample(UserEntity entity) {
        return userDao.getAllByExample(entity);
    }

    public void clear() {
        userDao.clear();
    }

    public void flush() {
        userDao.flush();
    }
}