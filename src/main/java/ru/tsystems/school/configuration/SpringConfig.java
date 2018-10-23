package ru.tsystems.school.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("ru.tsystems.school")
@Import(HibernateConfig.class)
public class SpringConfig {

}