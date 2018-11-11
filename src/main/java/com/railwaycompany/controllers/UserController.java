package com.railwaycompany.controllers;

import com.railwaycompany.entities.User;
import com.railwaycompany.services.api.UserService;
import com.railwaycompany.services.exceptions.AlreadyRegisteredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    //    @Autowired
//    private SecurityService securityService;
//
//    @Autowired
//    private UserValidator userValidator;
//
//    @RequestMapping(value = "registration", method = RequestMethod.GET)
//    public String registration(Model model) {
//        model.addAttribute("userForm", new User());
//
//        return "registration";
//    }
//
//    @RequestMapping(value = "registration", method = RequestMethod.POST)
//    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
//        userValidator.validate(userForm, bindingResult);
//
//        if (bindingResult.hasErrors()) {
//            return "registration";
//        }
//
//        try {
//            userService.saveUser(userForm);
//        } catch (AlreadyRegisteredException e) {
//            String message = "User already registered";
//            LOG.warn(message);
//        }
//
//        securityService.autoLogin(userForm.getEmail(), userForm.getConfirmPassword());
//
//        return "redirect:/index";
//    }
//
//    @RequestMapping(value = "login", method = RequestMethod.GET)
//    public String login(Model model, String error, String logout) {
//        if (error != null) {
//            model.addAttribute("error", "Email or password is incorrect");
//        }
//
//        if (logout != null) {
//            model.addAttribute("message", "Logged out successfully");
//        }
//
//        return "login";
//    }

    @RequestMapping(value = {"admin-all-passengers"}, method = RequestMethod.GET)
    public String listUsers(ModelMap model) {

        List<User> userList = userService.getAllUsers();
        model.addAttribute("userList", userList);
        return "admin-all-passengers";
    }

    @RequestMapping(value = {"/new"}, method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("edit", false);
        return "registration";
    }

    @RequestMapping(value = {"new"}, method = RequestMethod.POST)
    public String saveUser(User user, BindingResult result, ModelMap model) {

        if (result.hasErrors()) {
            return "registration";
        }

        try {
            userService.saveUser(user);
        } catch (AlreadyRegisteredException e) {
            e.printStackTrace();
        }

        model.addAttribute("success", "User " + user.getEmail() + " registered successfully");
        return "success";
    }

    @RequestMapping(value = {"edit-{email}-user"}, method = RequestMethod.GET)
    public String editUser(@PathVariable String email, ModelMap model) {
        User user = userService.getUserByEmail(email);
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        return "registration";
    }

    @RequestMapping(value = {"edit-{email}-user"}, method = RequestMethod.POST)
    public String updateUser(User user, BindingResult result, ModelMap model, @PathVariable String email) {

        if (result.hasErrors()) {
            return "registration";
        }

        userService.updateUser(user);

        model.addAttribute("success", "User " + user.getEmail() + " updated successfully");
        return "success";
    }

    @RequestMapping(value = {"delete-{email}-user"}, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String email) {
        userService.deleteUser(userService.getUserByEmail(email));
        return "redirect:/list";
    }

}