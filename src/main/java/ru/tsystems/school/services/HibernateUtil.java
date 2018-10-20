package ru.tsystems.school.services;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory = null;

    static {
        try {
            Configuration configuration = new Configuration()
                    .setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
                    .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/railways?useSSL=false")
                    .setProperty("hibernate.connection.username", "root")
                    .setProperty("hibernate.connection.password", "root")
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect")
                    .setProperty("hibernate.show_sql", "true")
                    .setProperty("hibernate.connection.autocommit", "false")
//                    .setProperty("hibernate.connection.pool_size", "5")
                    .setProperty("hibernate.hbm2ddl.auto", "update")
                    .setProperty("hibernate.c3p0.min_size", "5")
                    .setProperty("hibernate.c3p0.max_size", "20")
                    .setProperty("hibernate.c3p0.timeout", "1800")
                    .setProperty("hibernate.c3p0.max_statements", "50")

                    .addAnnotatedClass(ru.tsystems.school.entities.User.class);

            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties());

            sessionFactory = configuration.buildSessionFactory(builder.build());
        } catch (HibernateException e) {
            System.out.println("Ошибка HibernateUtil");
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
