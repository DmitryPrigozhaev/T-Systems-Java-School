package ru.tsystems.school;

import ru.tsystems.school.entities.User;
import ru.tsystems.school.services.UserService;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserService();
        User user = userService.readUser(1);
        System.out.println(user);
        userService.createUser(user);
    }
}
