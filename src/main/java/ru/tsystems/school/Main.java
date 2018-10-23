package ru.tsystems.school;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.tsystems.school.configuration.SpringConfig;
import ru.tsystems.school.entities.User;
import ru.tsystems.school.services.UserService;

public class Main {

    public static void main(String[] args) {

        /*
         *  Попытки тестировать систему
         */

        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserService service = (UserService) context.getBean("userService");

        User user = new User("Логин Василия", "Пароль Василия", "Почта Василия", "Василий", "Васильев");
        service.save(user);

//        service.save(new User("Логин Василия", "Пароль Василия", "Почта Василия", "Василий", "Васильев"));

    }
}
