package com.railwaycompany.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class TempController {

    @RequestMapping(value = "registration", method = RequestMethod.GET)
    public String registration() {
        return "registration";
    }
}
