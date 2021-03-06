package com.railwaycompany.controllers;

import com.railwaycompany.dto.*;
import com.railwaycompany.services.api.*;
import com.railwaycompany.services.exceptions.StationDoesNotExistException;
import com.railwaycompany.services.exceptions.TrainDoesNotExistException;
import com.railwaycompany.utils.DateConverter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class FindTrainController {

    private static final Logger LOG = Logger.getLogger(FindTrainController.class);

    @Autowired
    StationService stationService;

    @Autowired
    TicketService ticketService;

    @Autowired
    RouteService routeService;

    @Autowired
    TrainService trainService;

    @GetMapping(value = "find_train")
    public String findTrain(/*ModelMap modelMap*/) {
        return "find_train";
    }

    @ResponseBody
    @PostMapping(value = "find_train", consumes = "application/json")
    public List<TrainDto> findTrain(@RequestBody ScheduleDto scheduleDto) {

        StationDto stationFrom = null;
        StationDto stationTo = null;
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
                TrainDto trainDto = null;
                try {
                    trainDto = trainService.getTrainDtoByRouteIdAndStations(route.getId(), stationFrom.getName(), stationTo.getName());
                } catch (TrainDoesNotExistException e) {
                    String message = "Train does not exist";
                    LOG.warn(message);
                } catch (StationDoesNotExistException e) {
                    String message = "Station does not exist";
                    LOG.warn(message);
                }
                trainDtoList.add(trainDto);
            }
        }

        return trainDtoList;
    }

    @ResponseBody
    @PostMapping(value = "find-ticket", consumes = "application/json")
    public List<TicketDto> findTickets(@RequestBody TicketDto ticketDto) {
        return ticketService.getAllTicketsByTrainNumberAndCarriageAndStations(
                ticketDto.getTrainNumber(), ticketDto.getCarriageNumber(), ticketDto.getStationFromName(), ticketDto.getStationToName());
    }
}