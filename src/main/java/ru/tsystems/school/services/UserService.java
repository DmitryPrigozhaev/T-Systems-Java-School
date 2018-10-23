package ru.tsystems.school.services;

import ru.tsystems.school.entities.User;

import java.io.Serializable;
import java.util.List;

public interface UserService {

    public User getById(Serializable id);

    public User getByLogin(String login);

    public User getByEmail(String email);

    public List<User> getAll();

    public List<User> getAllByExample(User entity);

    public Serializable save(User entity);

    public void saveOrUpdate(User entity);

    public void delete(User entity);

    public void deleteById(Serializable id);

    public void deleteAll();

    public void clear();

    public void flush();
}