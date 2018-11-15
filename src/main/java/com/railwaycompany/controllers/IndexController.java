package com.railwaycompany.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    @RequestMapping(value = {"/", "index"}, method = RequestMethod.GET)
    public String getIndexPage() {
        return "index";
    }

//    @RequestMapping(value = {"/", "index"}, method = RequestMethod.POST)
//    public String searchTrain(@RequestParam(value = "stationFrom") String stationFrom,
//                              @RequestParam(value = "stationTo") String stationTo,
//                              @RequestParam(value = "date") String date,
//                              ModelMap modelMap) {
//        modelMap.addAttribute("stationFrom", stationFrom);
//        modelMap.addAttribute("stationTo", stationTo);
//        modelMap.addAttribute("date", date);
//        return "redirect:/searchTrain";
//    }

}