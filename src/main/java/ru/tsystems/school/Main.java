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
        UserService service = context.getBean(UserService.class);

        User user = service.getById((long)1);
        System.out.println(user);

        service.delete(user);

//        service.save(new User("Василий", "Васильев", "Почта Василия", "Логин Василия", "Пароль Василия"));
//        service.save(new User("Иван", "Иванов", "Почта Ивана", "Логин Ивана", "Пароль Ивана"));
//        service.save(new User("Сергей", "Сергеев", "Почта Сергея", "Логин Сергея", "Пароль Сергея"));

    }
}
