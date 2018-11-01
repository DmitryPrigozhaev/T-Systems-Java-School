package com.railwaycompany.controllers;

import com.railwaycompany.dto.UserDto;
import com.railwaycompany.entities.User;
import com.railwaycompany.exceptions.AlreadyRegisteredException;
import com.railwaycompany.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class AppController {

    /**
     * *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
     * *  *  *  *  *  * Тестирование *  *  *  *  *  *  *  *
     * *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
     */

    // TODO разобраться с PathVariable
    // PathVariable  показывает, что параметр метода должен быть связан с переменной из URL-адреса.

    @Autowired
    UserService userService;

    @Autowired
    MessageSource messageSource;

    // метод возвращает список всех существующих users
    @RequestMapping(value = {"list"}, method = RequestMethod.GET)
    public String listUsers(ModelMap model) {

        List<UserDto> usersDtoList = userService.getAllDtoUsers();
        model.addAttribute("users", usersDtoList);
        return "allusers";
    }

    // промежуточная стадия добавления user'а
    @RequestMapping(value = {"new"}, method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("edit", false);
        return "registration";
    }

    // метод вызовется при отправке формы методом POST
    // для сохранения user'а в БД. Также проверяет ввод пользователя
    @RequestMapping(value = "new", method = RequestMethod.POST)
    public String saveUser(User user, BindingResult result, ModelMap model) throws AlreadyRegisteredException {

        if (result.hasErrors()) {
            System.out.println("ОШИБКА!");
            System.out.println(result.getFieldError());
            return "registration";
        }

        userService.addUser(user);

        model.addAttribute("success", "User " + user.getFirstName() + " " + user.getLastName() + " registered successfully");
        return "success";
    }

    // промежуточная стадия редактирования user'а
    @RequestMapping(value = {"edit-{email}-user"}, method = RequestMethod.GET)
    public String editUser(@PathVariable String email, ModelMap model) {
        UserDto userDto = userService.getUserDtoByEmail(email);
        model.addAttribute("user", userDto);
        model.addAttribute("edit", true);
        return "registration";
    }

    // метод вызовется при отправке формы методом POST
    // для обновления user'а в БД. Также проверяет ввод пользователя
    @RequestMapping(value = {"edit-{email}-user"}, method = RequestMethod.POST)
    public String updateUser(User user, BindingResult result, ModelMap model, @PathVariable String email) {

        if (result.hasErrors()) {
            return "registration";
        }

        userService.updateUser(user);

        model.addAttribute("success", "User " + user.getFirstName() + " " + user.getLastName() + " updated successfully");
        return "success";
    }

}