package com.railwaycompany.controllers;

import com.railwaycompany.entities.Route;
import com.railwaycompany.entities.Train;
import com.railwaycompany.services.api.RouteService;
import com.railwaycompany.services.api.TrainService;
import com.railwaycompany.services.exceptions.RouteDoesNotExist;
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
        List<Train> trainList = trainService.getAllTrains();
        model.addAttribute("trainList", trainList);
        return "admin-all-trains";
    }

    @RequestMapping(value = "new-train", method = RequestMethod.GET)
    public String newTrain(ModelMap model) {
        List<Route> routeList = routeService.getAllRoutes();
        model.addAttribute("routeList", routeList);
        model.addAttribute("edit", false);
        return "add-train";
    }

    @RequestMapping(value = "new-train", method = RequestMethod.POST)
    public String saveTrain(@RequestParam("routeId") long routeId,
                           @RequestParam("number") int number,
                           @RequestParam("numberOfCarriages") int numberOfCarriages,
                           ModelMap model) {
        System.out.println(routeId);
        System.out.println(number);
        System.out.println(numberOfCarriages);

        Route route = null;
        try {
            route = routeService.getRouteById(routeId);
        } catch (RouteDoesNotExist routeDoesNotExist) {
            routeDoesNotExist.printStackTrace();
        }

        Train train = new Train();
        train.setRoute(route);
        train.setNumber(number);
        train.setNumberOfCarriages(numberOfCarriages);
        try {
            trainService.addTrain(train);
        } catch (TrainWithSuchNumberExistException e) {
            String message = "Train with number = " + number + " already exist";
            LOG.warn(message, e);
        }

        model.addAttribute("success", "Train number " + train.getNumber() + " registered successfully");
        return "train-success";
    }


    @RequestMapping(value = {"edit-{number}-train"}, method = RequestMethod.GET)
    public String editTrain(@PathVariable int number, ModelMap model) {
        Train train = null;

        try {
            train = trainService.getTrainByNumber(number);
        } catch (TrainDoesNotExistException e) {
            String message = "Train with number " + number + " does not exist";
            LOG.warn(message, e);
        }

        model.addAttribute("train", train);
        model.addAttribute("edit", true);
        return "add-train";
    }

    @RequestMapping(value = {"edit-{number}-train"}, method = RequestMethod.POST)
    public String updateTrain(@PathVariable int number, Train train, ModelMap model) {

        trainService.updateTrain(train);

        model.addAttribute("success", "Train " + train.getNumber() + " updated successfully");
        return "train-success";
    }

    @RequestMapping(value = {"delete-{number}-train"}, method = RequestMethod.GET)
    public String deleteTrain(@PathVariable int number) {
        try {
            Train train = trainService.getTrainByNumber(number);
            trainService.deleteTrain(train);
        } catch (TrainDoesNotExistException e) {
            String message = "Train number " + number + " does not exist";
            LOG.warn(message, e);
        }
        return "redirect:/admin-all-trains";
    }
}
