package ru.tsystems.school.util.config;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.tsystems.school.entities.UserEntity;

public class HibernateConfig {
    public static SessionFactory configureSessionFactory() {
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

                    .addAnnotatedClass(UserEntity.class);

            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties());

            return configuration.buildSessionFactory(builder.build());

        } catch (HibernateException e) {
            System.err.println("Ошибка файла конфигурации Hibernate");
            e.printStackTrace();
        }
        return null;
    }
}
