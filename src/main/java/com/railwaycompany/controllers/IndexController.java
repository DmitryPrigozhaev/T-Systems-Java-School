package com.railwaycompany.controllers;

import com.railwaycompany.aTest.MessageSenderNew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    @Autowired
    MessageSenderNew messageSender;

    @RequestMapping(value = {"/", "index"}, method = RequestMethod.GET)
    public String getIndexPage() {
        messageSender.sendMessage("test");
        return "index";
    }

//    @RequestMapping(value = {"/", "index"}, method = RequestMethod.POST)
//    public String searchTrain(@RequestBody ScheduleDto scheduleDto, ModelMap modelMap) {
//
//        return "redirect:/find-schedule";
//    }

}