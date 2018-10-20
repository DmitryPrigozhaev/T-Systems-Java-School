package ru.tsystems.school.dao;

import ru.tsystems.school.entities.UserEntity;

import java.util.List;

public interface UserDao {

    void create(UserEntity userEntity);

    UserEntity read(int id);

    void update(UserEntity userEntity);

    void delete(UserEntity userEntity);

    List<UserEntity> readAll();
}