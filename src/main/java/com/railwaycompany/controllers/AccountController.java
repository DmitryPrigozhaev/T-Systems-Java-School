package com.railwaycompany.controllers;

import com.railwaycompany.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AccountController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "account", method = {RequestMethod.GET, RequestMethod.POST})
    public String account() {
        return "account";
    }

}
