package ru.tsystems.school.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.tsystems.school.entities.User;
import ru.tsystems.school.services.UserService;

import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String listUsers(Map<String, Object> map) {
        map.put("user", new User());
        map.put("userList", userService.getAll());
        return "user";
    }

    @RequestMapping("/")
    public String home() {
        return "redirect:/index";
    }

    // ModelAttribute связывает параметр метода или возвращаемое значение метода
    // с атрибутом модели, которая будет использоваться при выводе jsp-страницы.
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user, BindingResult result){
        userService.save(user);
        return "redirect:/index";
    }

    // PathVariable  показывает, что параметр метода должен быть связан с переменной из URL-адреса.
    @RequestMapping("/delete/{userId}")
    public String deleteUserById(@PathVariable("userId") Integer userId) {
        userService.deleteById(userId);
        return "redirect:/index";
    }

}
