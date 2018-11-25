package com.railwaycompany.controllers;

import com.railwaycompany.dto.RouteDto;
import com.railwaycompany.dto.TrainDto;
import com.railwaycompany.services.api.RouteService;
import com.railwaycompany.services.api.TrainService;
import com.railwaycompany.services.exceptions.RouteDoesNotExistException;
import com.railwaycompany.services.exceptions.TrainDoesNotExistException;
import com.railwaycompany.services.exceptions.TrainWithSuchNumberExistException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TrainController {

    private static final Logger LOG = Logger.getLogger(TrainController.class);

    @Autowired
    private TrainService trainService;

    @Autowired
    private RouteService routeService;

    @RequestMapping(value = "admin-all-trains", method = RequestMethod.GET)
    public String getAllTrains(ModelMap model) {
        List<TrainDto> trainList = trainService.getAllTrains();
        model.addAttribute("trainList", trainList);
        return "admin-all-trains";
    }

    @RequestMapping(value = "new-train", method = RequestMethod.GET)
    public String newTrain(ModelMap model) {
        List<RouteDto> routeList = routeService.getAllRoutes();
        model.addAttribute("routeList", routeList);
        model.addAttribute("edit", false);
        return "add-train";
    }

    @RequestMapping(value = "new-train", method = RequestMethod.POST)
    public String saveTrain(@RequestParam("routeId") long routeId,
                            @RequestParam("number") int number,
                            @RequestParam("numberOfCarriages") int numberOfCarriages,
                            ModelMap model) {

        RouteDto routeDto = null;
        try {
            routeDto = routeService.getRouteDtoById(routeId);
        } catch (RouteDoesNotExistException routeDoesNotExistException) {
            routeDoesNotExistException.printStackTrace();
        }

        TrainDto trainDto = new TrainDto();
        trainDto.setNumber(number);
        trainDto.setRouteName(routeDto == null ? "" : routeDto.getName());
        trainDto.setNumberOfCarriages(numberOfCarriages);

        try {
            trainService.addTrain(trainDto);
        } catch (TrainWithSuchNumberExistException e) {
            String message = "Train with number = " + number + " already exist";
            LOG.warn(message, e);
        }

        model.addAttribute("success", "Train number " + trainDto.getNumber() + " registered successfully");
        return "train-success";
    }


    @RequestMapping(value = {"edit-{number}-train"}, method = RequestMethod.GET)
    public String editTrain(@PathVariable int number, ModelMap model) {
        TrainDto trainDto = null;

        try {
            trainDto = trainService.getTrainDtoByNumber(number);
        } catch (TrainDoesNotExistException e) {
            String message = "Train with number " + number + " does not exist";
            LOG.warn(message, e);
        }

        List<RouteDto> routeList = routeService.getAllRoutes();
        model.addAttribute("routeList", routeList);
        model.addAttribute("train", trainDto);
        model.addAttribute("edit", true);
        return "add-train";
    }

    @RequestMapping(value = {"edit-{number}-train"}, method = RequestMethod.POST)
    public String updateTrain(@PathVariable int number, TrainDto trainDto, ModelMap model) {

        trainService.updateTrain(trainDto);

        model.addAttribute("success", "Train " + trainDto.getNumber() + " updated successfully");
        return "train-success";
    }

    @RequestMapping(value = {"delete-{number}-train"}, method = RequestMethod.GET)
    public String deleteTrain(@PathVariable int number) {
        try {
            TrainDto trainDto = trainService.getTrainDtoByNumber(number);
            trainService.deleteTrain(trainDto);
        } catch (TrainDoesNotExistException e) {
            String message = "Train number " + number + " does not exist";
            LOG.warn(message, e);
        }
        return "redirect:/admin-all-trains";
    }
}
