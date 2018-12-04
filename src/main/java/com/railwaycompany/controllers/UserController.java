package com.railwaycompany.controllers;

import com.railwaycompany.dto.UserDto;
import com.railwaycompany.services.api.UserService;
import com.railwaycompany.services.exceptions.AlreadyRegisteredException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    private static final Logger LOG = Logger.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @GetMapping(value = "login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "registration")
    public ModelAndView registration() {
        return new ModelAndView("registration", "user", new UserDto());
    }

    @PostMapping(value = "registration")
    public String registration(@ModelAttribute("user") UserDto userDto,
                               BindingResult bindingResult,
                               ModelMap modelMap) {

        if (bindingResult.hasErrors()) return "registration";

        try {
            userService.saveUserDto(userDto);
        } catch (AlreadyRegisteredException e) {
            String message = "User with email \"" + userDto.getEmail() + "\" is already exist";
            LOG.warn(message);
        }
        return "redirect:/account";
    }

}