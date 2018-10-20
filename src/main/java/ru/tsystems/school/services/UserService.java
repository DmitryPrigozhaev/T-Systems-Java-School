package ru.tsystems.school.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.school.dao.UserDao;
import ru.tsystems.school.dao.UserDaoImpl;
import ru.tsystems.school.entities.UserEntity;

import java.util.List;

@Service("userService")
@Transactional
public class UserService {

    private UserDao userDao = new UserDaoImpl();

    public void createUser(UserEntity user) {
        userDao.create(user);
    }

    public UserEntity readUser(int id) {
        return userDao.read(id);
    }

    public void updateUser(UserEntity user) {
        userDao.update(user);
    }

    public void deleteUser(UserEntity user) {
        userDao.delete(user);
    }

    public List<UserEntity> readAllUser() {
        return userDao.readAll();
    }

}
