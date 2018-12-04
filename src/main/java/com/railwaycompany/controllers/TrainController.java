package com.railwaycompany.controllers;

import com.railwaycompany.dto.RouteDto;
import com.railwaycompany.dto.TrainDto;
import com.railwaycompany.services.api.RouteService;
import com.railwaycompany.services.api.TrainService;
import com.railwaycompany.services.exceptions.RouteDoesNotExistException;
import com.railwaycompany.services.exceptions.TrainDoesNotExistException;
import com.railwaycompany.services.exceptions.TrainWithSuchNumberExistException;
import com.railwaycompany.services.exceptions.TrainWithSuchRouteExistException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TrainController {

    private static final Logger LOG = Logger.getLogger(TrainController.class);

    @Autowired
    private TrainService trainService;

    @Autowired
    private RouteService routeService;

    @GetMapping(value = "admin-all-trains")
    public String getAllTrains(ModelMap model) {
        List<TrainDto> trainList = trainService.getAllTrains();
        model.addAttribute("trainList", trainList);
        return "admin-all-trains";
    }

    @GetMapping(value = "new-train")
    public String newTrain(ModelMap model) {
        TrainDto trainDto = new TrainDto();
        List<RouteDto> routeList = routeService.getAllRoutes();
        model.addAttribute("edit", false);
        model.addAttribute("routeList", routeList);
        model.addAttribute("trainDto", trainDto);
        return "add-train";
    }

    @PostMapping(value = "new-train")
    public String saveTrain(@RequestParam(name = "number") int number,
                            @RequestParam(name = "numberOfCarriages") int numberOfCarriages,
                            @RequestParam(name = "routeName", required = false) String routeName,
                            ModelMap model) {

        RouteDto routeDto = null;
        try {
            routeDto = routeService.getRouteDtoByName(routeName);
        } catch (RouteDoesNotExistException e) {
            String message = "Route \"" + routeName + "\" does not exist";
            LOG.warn(message);
        }

        TrainDto trainDto = new TrainDto();
        trainDto.setNumber(number);
        trainDto.setRouteName(routeDto == null ? "" : routeDto.getName());
        trainDto.setNumberOfCarriages(numberOfCarriages);

        try {
            trainService.addTrain(trainDto);
            model.addAttribute("saveTrainSuccess", true);
            model.addAttribute("success", "Train №" + trainDto.getNumber() + " registered successfully");
        } catch (TrainWithSuchNumberExistException e) {
            model.addAttribute("saveTrainFailure", true);
            model.addAttribute("failure", "Cannot create train:\ntrain №" + number + " already exist");
        } catch (TrainWithSuchRouteExistException e) {
            model.addAttribute("saveTrainFailure", true);
            model.addAttribute("failure", "Cannot create train:\ntrain with route \"" + trainDto.getRouteName() + "\" already exist");
        }

        List<RouteDto> routeList = routeService.getAllRoutes();
        model.addAttribute("routeList", routeList);
        return "add-train";
    }

    @GetMapping(value = "edit-{number}-train")
    public String editTrain(@PathVariable int number, ModelMap model) {

        TrainDto trainDto = null;
        try {
            trainDto = trainService.getTrainDtoByNumber(number);
        } catch (TrainDoesNotExistException e) {
            String message = "Train №" + number + " does not exist";
            LOG.warn(message, e);
        }

        List<RouteDto> routeList = routeService.getAllRoutes();
        model.addAttribute("edit", true);
        model.addAttribute("routeList", routeList);
        model.addAttribute("trainDto", trainDto);
        return "add-train";
    }

    @PostMapping(value = "edit-{number}-train")
    public String updateTrain(TrainDto trainDto, ModelMap model) {

        try {
            trainService.updateTrain(trainDto);
            model.addAttribute("updateTrainSuccess", true);
            model.addAttribute("success", "Train №" + trainDto.getNumber() + "\" updated successfully");
        } catch (TrainWithSuchNumberExistException e) {
            model.addAttribute("updateTrainFailure", true);
            model.addAttribute("failure", "Cannot update train №" + trainDto.getNumber() + ":\ntrain with such number already exist");
        } catch (TrainWithSuchRouteExistException e) {
            model.addAttribute("updateTrainFailure", true);
            model.addAttribute("failure", "Cannot update train:\ntrain with route \"" + trainDto.getRouteName() + "\" already exist");
        }

        List<RouteDto> routeList = routeService.getAllRoutes();
        model.addAttribute("edit", true);
        model.addAttribute("routeList", routeList);
        model.addAttribute("trainDto", trainDto);
        return "add-train";
    }

    @GetMapping(value = "delete-{number}-train")
    public String deleteTrain(@PathVariable int number) {
        try {
            TrainDto trainDto = trainService.getTrainDtoByNumber(number);
            trainService.deleteTrain(trainDto);
        } catch (TrainDoesNotExistException e) {
            String message = "Train №" + number + " does not exist";
            LOG.warn(message, e);
        }
        return "redirect:/admin-all-trains";
    }
}
