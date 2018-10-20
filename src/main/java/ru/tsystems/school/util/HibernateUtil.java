package ru.tsystems.school.util;

import org.hibernate.SessionFactory;
import ru.tsystems.school.util.config.HibernateConfig;

public final class HibernateUtil {

    private static SessionFactory sessionFactory = HibernateConfig.configureSessionFactory();;

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
