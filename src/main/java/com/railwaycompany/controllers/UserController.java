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
            userService.saveUserDto(userDto);
        } catch (AlreadyRegisteredException e) {
            String message = "User with email \"" + userDto.getEmail() + "\" is already exist";
            LOG.warn(message);
        }
        return "redirect:/account";
    }

    @RequestMapping(value = {"admin-all-passengers"}, method = RequestMethod.GET)
    public String listUsers(ModelMap model) {

        List<UserDto> userList = userService.getAllUsersDto();
        model.addAttribute("userList", userList);
        return "admin-all-passengers";
    }

    // todo don't use
    @RequestMapping(value = {"/new"}, method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("edit", false);
        return "registration";
    }

    // todo don't use
    @RequestMapping(value = {"new"}, method = RequestMethod.POST)
    public String saveUser(UserDto userDto, BindingResult result, ModelMap model) {

        if (result.hasErrors()) {
            return "registration";
        }

        try {
            userService.saveUserDto(userDto);
        } catch (AlreadyRegisteredException e) {
            e.printStackTrace();
        }

        model.addAttribute("success", "User " + userDto.getEmail() + " registered successfully");
        return "success";
    }

    @RequestMapping(value = {"edit-{email}-user"}, method = RequestMethod.GET)
    public String editUser(@PathVariable String email, ModelMap model) {
        UserDto user = userService.getUserDtoByEmail(email);
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        return "registration";
    }

    @RequestMapping(value = {"edit-{email}-user"}, method = RequestMethod.POST)
    public String updateUser(UserDto userDto, BindingResult result, ModelMap model, @PathVariable String email) {

        if (result.hasErrors()) {
            return "registration";
        }

        userService.updateUserDto(userDto);

        model.addAttribute("success", "User " + userDto.getEmail() + " updated successfully");
        return "success";
    }

    @RequestMapping(value = {"delete-{email}-user"}, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String email) {
        userService.deleteUserDto(userService.getUserDtoByEmail(email));
        return "redirect:/list";
    }

}