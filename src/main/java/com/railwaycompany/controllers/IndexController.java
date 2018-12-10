package com.railwaycompany.controllers;

import com.railwaycompany.dto.ScheduleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

//    @Autowired
//    MessageSender messageSender;

    @RequestMapping(value = {"/", "index"}, method = RequestMethod.GET)
    public String getIndexPage() {
//        messageSender.sendMessage("test");
        return "index";
    }

//    @RequestMapping(value = {"/", "index"}, method = RequestMethod.POST)
//    public String searchTrain(@RequestBody ScheduleDto scheduleDto, ModelMap modelMap) {
//
//        return "redirect:/find-schedule";
//    }

}