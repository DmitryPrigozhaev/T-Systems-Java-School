package ru.tsystems.school;

import ru.tsystems.school.entities.UserEntity;
import ru.tsystems.school.services.UserService;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserService();

        UserEntity user = new UserEntity("qwe", "zxcczc", "asdxc", "aszxczxc", "zxczxcsd");
        userService.createUser(user);
    }
}
