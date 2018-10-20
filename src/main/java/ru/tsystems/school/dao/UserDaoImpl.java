package ru.tsystems.school.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.tsystems.school.entities.User;
import ru.tsystems.school.services.HibernateUtil;

import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public void create(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public User read(int id) {
        return HibernateUtil.getSessionFactory().openSession().get(User.class, id);
    }

    @Override
    public void update(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> readAll() {
        List<User> users = (List<User>) HibernateUtil.getSessionFactory().openSession().createCriteria(User.class).list();
        return users;
    }
}