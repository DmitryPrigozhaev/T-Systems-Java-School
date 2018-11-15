package com.railwaycompany.controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ScheduleController {

    private static final Logger LOG = Logger.getLogger(ScheduleController.class);



    @RequestMapping(value = "schedule", method = RequestMethod.GET)
    public String searchTrain(ModelMap modelMap) {
        return "schedule";
    }

    @RequestMapping(value = "schedule", method = RequestMethod.POST)
    public String searchTrain(@RequestParam(value = "stationFrom") String stationFrom,
                              @RequestParam(value = "stationTo") String stationTo,
                              @RequestParam(value = "date") String date,
                              ModelMap modelMap) {

        modelMap.addAttribute("stationFrom", stationFrom);
        modelMap.addAttribute("stationTo", stationTo);
        modelMap.addAttribute("date", date);

        if (stationFrom != null && stationTo != null && date != null) {

        }

        return "redirect:/searchTrain";
    }

}
