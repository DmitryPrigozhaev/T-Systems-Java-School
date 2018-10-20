package ru.tsystems.school.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.school.dao.UserDao;
import ru.tsystems.school.entities.UserEntity;

import java.io.Serializable;
import java.util.List;

@Service("userService")
@Transactional(readOnly = true)
public class UserService {

    @Autowired
    UserDao userDao;

    public List<UserEntity> getAllUsers() {
        return userDao.findAll();
    }

    //TODO разобраться с методом
    //добавить нового юзера, если такого не существует
    @Transactional(readOnly = false)
    public void addNewUser(UserEntity userEntity) {
        UserEntity user = new UserEntity();
        user.setLogin(userEntity.getLogin());
        user.setEmail(userEntity.getEmail());
        List<UserEntity> list = userDao.findAllByExample(user);
        if (list == null || list.isEmpty()) {
            Long id = (Long) userDao.save(userEntity);
        } else {
            System.out.println("Пользователь уже существует!");
        }
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
        return userDao.findAll();
    }

    public UserEntity findById(Serializable id) {
        return userDao.findById(id);
    }

    public List<UserEntity> findAllByExample(UserEntity entity) {
        return userDao.findAllByExample(entity);
    }

    public void clear() {
        userDao.clear();
    }

    public void flush() {
        userDao.flush();
    }
}