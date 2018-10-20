package ru.tsystems.school;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.tsystems.school.entities.UserEntity;
import ru.tsystems.school.services.UserService;
import ru.tsystems.school.util.config.SpringConfig;

public class Main {


    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        UserService userService = new UserService();

        UserEntity user = new UserEntity("Вася", "Уткин", "Вася@почта.ру", "ВасяЛогин", "ВасяПароль");
        //userService.addNewUser(user);

        userService.save(user);
    }
}
