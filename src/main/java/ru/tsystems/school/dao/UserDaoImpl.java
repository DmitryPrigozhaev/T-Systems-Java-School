package ru.tsystems.school.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.tsystems.school.entities.UserEntity;
import ru.tsystems.school.util.HibernateUtil;

import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public void create(UserEntity userEntity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(userEntity);
        transaction.commit();
        session.close();
    }

    @Override
    public UserEntity read(int id) {
        return HibernateUtil.getSessionFactory().openSession().get(UserEntity.class, id);
    }

    @Override
    public void update(UserEntity userEntity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(userEntity);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(UserEntity userEntity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(userEntity);
        transaction.commit();
        session.close();
    }

    @Override
    public List<UserEntity> readAll() {
        List<UserEntity> users = (List<UserEntity>) HibernateUtil.getSessionFactory().openSession().createCriteria(UserEntity.class).list();
        return users;
    }
}