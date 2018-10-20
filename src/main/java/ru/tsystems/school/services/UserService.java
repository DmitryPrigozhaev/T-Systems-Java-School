package ru.tsystems.school.services;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("userService")
@Transactional
public class UserService {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

//    public UserEntity getUser(int id) {
//
//    }

//    private UserDao userDao = new UserDaoImpl();
//
//    public void createUser(UserEntity user) {
//        userDao.create(user);
//    }
//
//    public UserEntity readUser(int id) {
//        return userDao.read(id);
//    }
//
//    public void updateUser(UserEntity user) {
//        userDao.update(user);
//    }
//
//    public void deleteUser(UserEntity user) {
//        userDao.delete(user);
//    }
//
//    public List<UserEntity> readAllUser() {
//        return userDao.readAll();
//    }

}
