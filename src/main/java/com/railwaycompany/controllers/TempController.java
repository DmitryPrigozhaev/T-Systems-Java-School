package com.railwaycompany.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;


@Controller
public class TempController {

//    @RequestMapping(value = "registration", method = RequestMethod.GET)
//    public String registration() {
//        return "registration";
//    }

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String test(Model model, Principal principal) {
        model.addAttribute("message", "You are logged in as " + principal.getName());
        return "index";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

}
