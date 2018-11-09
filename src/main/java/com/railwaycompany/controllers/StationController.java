package com.railwaycompany.controllers;

import com.railwaycompany.entities.Station;
import com.railwaycompany.services.api.StationService;
import com.railwaycompany.services.exceptions.StationWithSuchNameDoesNotExistException;
import com.railwaycompany.services.exceptions.StationWithSuchNameExistException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class StationController {

    private static final Logger LOG = Logger.getLogger(StationController.class);

    @Autowired
    private StationService stationService;

    @RequestMapping(value = "admin-all-stations", method = RequestMethod.GET)
    public String getAllStations(ModelMap model) {
        List<Station> stationList = stationService.getAllStation();
        model.addAttribute("stationList", stationList);
        return "admin-all-stations";
    }

    @RequestMapping(value = "new-station", method = RequestMethod.GET)
    public String newStation(ModelMap model) {
        Station station = new Station();
        model.addAttribute("station", station);
        model.addAttribute("edit", false);
        return "add-station";
    }

    @RequestMapping(value = "new-station", method = RequestMethod.POST)
    public String saveStation(Station station, ModelMap model) throws StationWithSuchNameExistException {

        stationService.addStation(station);

        model.addAttribute("success", "Station " + station.getName() + " registered successfully");
        return "station-success";
    }

    @RequestMapping(value = {"edit-{name}-station"}, method = RequestMethod.GET)
    public String editStation(@PathVariable String name, ModelMap model) {
        Station station = null;
        try {
            station = stationService.getStationByName(name);
        } catch (StationWithSuchNameDoesNotExistException e) {
            e.printStackTrace();
        }
        model.addAttribute("station", station);
            model.addAttribute("edit", true);
        return "add-station";
    }

    @RequestMapping(value = {"edit-{name}-station"}, method = RequestMethod.POST)
    public String updateStation(Station station, ModelMap model, @PathVariable String name) {

        stationService.updateStation(station);

        model.addAttribute("success", "Station " + station.getName() + " updated successfully");
        return "station-success";
    }

    @RequestMapping(value = {"delete-{name}-station"}, method = RequestMethod.GET)
    public String deleteStation(@PathVariable String name) {
        try {
            Station station = stationService.getStationByName(name);
            stationService.deleteStation(station);
        } catch (StationWithSuchNameDoesNotExistException e) {
            String message = "Station with name = " + name + " does not exist";
            LOG.warn(message, e);
        }
        return "redirect:/admin-all-stations";
    }
}