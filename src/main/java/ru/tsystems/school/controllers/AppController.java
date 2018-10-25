package ru.tsystems.school.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.tsystems.school.entities.User;
import ru.tsystems.school.services.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/")
public class AppController {

    // TODO разобраться с PathVariable
    // PathVariable  показывает, что параметр метода должен быть связан с переменной из URL-адреса.

    @Autowired
    UserService userService;

    @Autowired
    MessageSource messageSource;

    // метод возвращает список всех существующих users
    @RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
    public String listUsers(ModelMap model) {

        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "allusers";
    }

    // промежуточная стадия добавления user'а
    @RequestMapping(value = {"/new"}, method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("edit", false);
        return "registration";
    }

    // метод вызовется при отправке формы методом POST
    // для сохранения user'а в БД. Также проверяет ввод пользователя
    @RequestMapping(value = {"/new"}, method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult result, ModelMap model) {

        if (result.hasErrors()) {
            return "registration";
        }

        if (!userService.isUserEmailUnique(user.getId(), user.getEmail())) {
            FieldError emailError = new FieldError("user", "email", messageSource.getMessage("non.unique.email", new String[]{user.getEmail()}, Locale.getDefault()));
            result.addError(emailError);
            return "registration";
        }

        userService.saveUser(user);

        model.addAttribute("success", "User " + user.getFirstName() + " " + user.getLastName() + " registered successfully");
        return "success";
    }

    // промежуточная стадия редактирования user'а
    @RequestMapping(value = {"/edit-{email}-user"}, method = RequestMethod.GET)
    public String editUser(@PathVariable String email, ModelMap model) {
        User user = userService.getUserByEmail(email);
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        return "registration";
    }

    // метод вызовется при отправке формы методом POST
    // для обновления user'а в БД. Также проверяет ввод пользователя
    @RequestMapping(value = {"/edit-{email}-user"}, method = RequestMethod.POST)
    public String updateUser(@Valid User user, BindingResult result,
                             ModelMap model, @PathVariable String email) {

        if (result.hasErrors()) {
            return "registration";
        }

        if (!userService.isUserEmailUnique(user.getId(), user.getEmail())) {
            FieldError emailError = new FieldError("user", "email", messageSource.getMessage("non.unique.email",
                    new String[]{user.getEmail()}, Locale.getDefault()));
            result.addError(emailError);
            return "registration";
        }

        userService.saveOrUpdateUser(user);

        model.addAttribute("success", "User " + user.getFirstName() + " " + user.getLastName() + " updated successfully");
        return "success";
    }

    // метод удалит user'а по значению email
    @RequestMapping(value = {"/delete-{email}-user"}, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String email) {
        userService.deleteUserByEmail(email);
        return "redirect:/list";
    }

}