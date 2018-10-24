package ru.tsystems.school;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.tsystems.school.configuration.SpringConfig;
import ru.tsystems.school.entities.User;
import ru.tsystems.school.services.UserService;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        /*
         *  Попытки тестировать систему
         */
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserService service = (UserService) context.getBean("userService");
//
        String date = "1923-12-12";
        LocalDate localDate = LocalDate.parse(date);
//
        User user = new User("Василий@gmail.com", "Пароль_Василия", "Василий", "Васильев", localDate);
//        User user = new User();
//        user.setPassword("123");
//        user.setEmail("123ssda");

        System.out.println(user);
        service.saveUser(user);

//        System.out.println(user);

    }
}
