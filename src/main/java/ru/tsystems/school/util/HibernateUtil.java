package ru.tsystems.school.util;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.tsystems.school.util.config.HibernateConfig;

@Component
public final class HibernateUtil {

    private static SessionFactory sessionFactory = HibernateConfig.configureSessionFactory();;

    @Bean
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
