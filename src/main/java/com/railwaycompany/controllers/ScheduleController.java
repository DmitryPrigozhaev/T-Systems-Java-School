package com.railwaycompany.controllers;

import com.railwaycompany.dto.RouteDto;
import com.railwaycompany.dto.ScheduleDto;
import com.railwaycompany.dto.TrainDto;
import com.railwaycompany.entities.Station;
import com.railwaycompany.services.api.RouteService;
import com.railwaycompany.services.api.StationService;
import com.railwaycompany.services.api.TrainService;
import com.railwaycompany.services.exceptions.StationDoesNotExistException;
import com.railwaycompany.utils.DateConverter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ScheduleController {

    private static final Logger LOG = Logger.getLogger(ScheduleController.class);

    @Autowired
    StationService stationService;

    @Autowired
    RouteService routeService;

    @Autowired
    TrainService trainService;


    @RequestMapping(value = "schedule", method = RequestMethod.GET)
    public String searchTrain(/*ModelMap modelMap*/) {
//        List<Station> stationList = stationService.getAllStation();
//        modelMap.addAttribute("stationList", stationList);
        return "schedule";
    }

    @ResponseBody
    @RequestMapping(value = "find-schedule", method = RequestMethod.POST, consumes = "application/json")
    public List<TrainDto> searchTrain(@RequestBody ScheduleDto scheduleDto) {

        Station stationFrom = null;
        Station stationTo = null;
        Date date = null;

        List<TrainDto> trainDtoList = null;
        List<RouteDto> routes = null;

        try {
            stationFrom = stationService.getStationByName(scheduleDto.getStationFromName());
        } catch (StationDoesNotExistException e) {
            String message = "Station with name \"" + scheduleDto.getStationFromName() + "\" does not exist";
            LOG.warn(message);
        }
        try {
            stationTo = stationService.getStationByName(scheduleDto.getStationToName());
        } catch (StationDoesNotExistException e) {
            String message = "Station with name \"" + scheduleDto.getStationFromName() + "\" does not exist";
            LOG.warn(message);
        }
        if (!scheduleDto.getDate().equals("")) date = DateConverter.convertDate(scheduleDto.getDate());

        if (date != null && stationFrom != null && stationTo != null) {
            routes = routeService.findRouteDtoByStationsAndDate(stationFrom, stationTo, date);
        } else if (date == null && stationFrom != null && stationTo != null) {
            routes = routeService.findRouteDtoByStations(stationFrom, stationTo);
        }

        if (routes != null) {
            trainDtoList = new ArrayList<>();
            for (RouteDto route : routes) {
                TrainDto trainDto = trainService.getTrainDtoByRouteIdAndStations(route.getId(), stationFrom.getName(), stationTo.getName());
                trainDtoList.add(trainDto);
            }
        }

        return trainDtoList;
    }

}