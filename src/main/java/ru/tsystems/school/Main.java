package ru.tsystems.school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.tsystems.school.entities.UserEntity;
import ru.tsystems.school.services.UserService;
import ru.tsystems.school.util.config.SpringConfig;

public class Main {

    @Autowired
    static UserService userService;

    public static void main(String[] args) {

        /*
         *  Попытки тестировать систему
         */

        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        userService.save(new UserEntity("Василий", "Петрович", "ВродеПочта", "Вроде Логин", "Вроде пароль"));


    }
}
