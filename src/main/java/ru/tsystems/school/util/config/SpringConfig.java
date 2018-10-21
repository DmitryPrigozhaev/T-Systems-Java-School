package ru.tsystems.school.util.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("ru.tsystems.school")
@Import(HibernateConfig.class)
public class SpringConfig {

}