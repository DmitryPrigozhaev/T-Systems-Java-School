package com.railwaycompany.controllers;

import com.railwaycompany.dto.UserDto;
import com.railwaycompany.entities.User;
import com.railwaycompany.services.api.UserService;
import com.railwaycompany.services.exceptions.AlreadyRegisteredException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    private static final Logger LOG = Logger.getLogger(UserController.class);

    @Autowired
    UserService userService;

//    @Autowired
//    private SecurityService securityService;

//    @Autowired
//    private UserValidator userValidator;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "registration", method = RequestMethod.GET)
    public ModelAndView registration() {
        return new ModelAndView("registration", "user", new UserDto());
    }

    @RequestMapping(value = "registration", method = RequestMethod.POST)
    public String registration(ModelMap modelMap, @ModelAttribute("user") UserDto userDto,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "registration";

        try {
            userService.saveUser(userDto);
        } catch (AlreadyRegisteredException e) {
            String message = "User with email \"" + userDto.getEmail() + "\" is already exist";
            LOG.warn(message);
        }
        return "redirect:/account";
    }

    @RequestMapping(value = "account", method = RequestMethod.GET)
    public String account() {
        return "account";
    }

//    @ResponseBody
//    @RequestMapping(value = "registration", method = RequestMethod.POST)
//    public String registration(@RequestBody UserDto userDto) {
//
//        User user = new User();
//        user.set
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
    public String saveUser(UserDto userDto, BindingResult result, ModelMap model) {

        if (result.hasErrors()) {
            return "registration";
        }

        try {
            userService.saveUser(userDto);
        } catch (AlreadyRegisteredException e) {
            e.printStackTrace();
        }

        model.addAttribute("success", "User " + userDto.getEmail() + " registered successfully");
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