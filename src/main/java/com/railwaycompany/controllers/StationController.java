package com.railwaycompany.controllers;

import com.railwaycompany.entities.Station;
import com.railwaycompany.services.api.StationService;
import com.railwaycompany.services.exceptions.StationDoesNotExistException;
import com.railwaycompany.services.exceptions.StationWithSuchNameExistException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "new-station")
    public String newStation(ModelMap model) {
        Station station = new Station();
        model.addAttribute("station", station);
        model.addAttribute("edit", false);
        return "add-station";
    }

    @PostMapping(value = "new-station")
    public String saveStation(Station station, ModelMap model) {

        try {
            stationService.addStation(station);
            model.addAttribute("saveStationSuccess", true);
            model.addAttribute("success", "Station \"" + station.getName() + "\" registered successfully");
        } catch (StationWithSuchNameExistException e) {
            model.addAttribute("saveStationFailure", true);
            model.addAttribute("failure", "Cannot create station: station \"" + station.getName() + "\" already exist");
        }

        return "add-station";
    }

    @GetMapping(value = "edit-{name}-station")
    public String editStation(@PathVariable String name, ModelMap model) {
        Station station = null;
        try {
            station = stationService.getStationByName(name);
        } catch (StationDoesNotExistException e) {
            String message = "Cannot update station \"" + name + "\": station does not exist!";
            LOG.warn(message);
        }
        model.addAttribute("station", station);
        model.addAttribute("edit", true);
        return "add-station";
    }

    @PostMapping(value = "edit-{name}-station")
    public String updateStation(Station station, ModelMap model) {

        try {
            stationService.updateStation(station);
            model.addAttribute("updateStationSuccess", true);
            model.addAttribute("success", "Station \"" + station.getName() + "\" updated successfully");
        } catch (StationWithSuchNameExistException e) {
            model.addAttribute("updateStationFailure", true);
            model.addAttribute("failure", "Cannot update station \"" + station.getName() + "\": station with such name already exist");
        }

        model.addAttribute("edit", true);
        return "add-station";
    }

    @GetMapping(value = "delete-{name}-station")
    public String deleteStation(@PathVariable String name) {
        Station station = null;
        try {
            station = stationService.getStationByName(name);
        } catch (StationDoesNotExistException e) {
            String message = "Station with name \"" + name + "\" does not exist";
            LOG.warn(message);
        }
        stationService.deleteStation(station);
        return "redirect:/admin-all-stations";
    }
}