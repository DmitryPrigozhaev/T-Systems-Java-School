package com.railwaycompany.controllers;

import com.railwaycompany.utils.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    MessageSender messageSender;

    @GetMapping(value = {"/", "index"})
    public String getIndexPage() {
        messageSender.sendMessage("test");
        return "index";
    }

}