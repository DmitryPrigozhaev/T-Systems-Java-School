package com.railwaycompany.controllers;

import com.railwaycompany.dto.RoutePointDto;
import com.railwaycompany.dto.ScheduleDto;
import com.railwaycompany.services.api.RouteService;
import com.railwaycompany.services.api.StationService;
import com.railwaycompany.utils.DateConverter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class StationScheduleController {

    @Autowired
    StationService stationService;

    @Autowired
    RouteService routeService;

    @GetMapping(value = "station_schedule")
    public String getStationSchedule(ModelMap modelMap) {
        return "station_schedule";
    }

    @ResponseBody
    @PostMapping(value = "station_schedule", consumes = "application/json")
    public List<RoutePointDto> findStationSchedule(@RequestBody ScheduleDto scheduleDto) {

        Date date;
        if (scheduleDto.getDate() != null) {
            date = DateConverter.convertDate(scheduleDto.getDate());
        } else {
            date = new Date();
        }

        return stationService.getStationScheduleByStationName(scheduleDto.getStationFromName(), date);
    }

    @ResponseBody
    @GetMapping(value = "schedule_by_{stationName}")
    public List<RoutePointDto> getAllRoutePoints(@PathVariable String stationName) {
        return stationService.getStationScheduleByStationName(stationName, new Date());
    }
}
