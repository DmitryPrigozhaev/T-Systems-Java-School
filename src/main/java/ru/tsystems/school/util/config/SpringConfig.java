package ru.tsystems.school.util.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.tsystems.school.dao.UserDaoImpl;

@Configuration
@ComponentScan("ru.tsystems.school")
@Import(HibernateConfig.class)
public class SpringConfig {

    @Bean(name="userDaoImpl")
    public UserDaoImpl getUserDaoImpl() {
        return new UserDaoImpl();
    }
}