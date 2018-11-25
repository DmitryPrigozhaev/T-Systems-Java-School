package com.railwaycompany.controllers;

import com.railwaycompany.dto.RouteDto;
import com.railwaycompany.dto.RoutePointDto;
import com.railwaycompany.entities.Station;
import com.railwaycompany.services.api.RouteService;
import com.railwaycompany.services.api.StationService;
import com.railwaycompany.services.exceptions.RouteDoesNotExistException;
import com.railwaycompany.services.exceptions.RouteWithSuchNameExistException;
import com.railwaycompany.services.exceptions.StationDoesNotExistException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RouteController {

    private static final Logger LOG = Logger.getLogger(RouteController.class);

    @Autowired
    private RouteService routeService;

    @Autowired
    private StationService stationService;

    @RequestMapping(value = "admin-all-routes", method = RequestMethod.GET)
    public String getAllRoutes(ModelMap model) {
        List<RouteDto> routeDtoList = routeService.getAllRoutes();
        List<RoutePointDto> routePointsDtoList = routeService.getAllRoutePointsDtoList();
        model.addAttribute("routeDtoList", routeDtoList);
        model.addAttribute("routePointsDtoList", routePointsDtoList);
        return "admin-all-routes";
    }

    @RequestMapping(value = "new-route", method = RequestMethod.GET)
    public String newRoute(ModelMap model) {
        List<Station> stationList = stationService.getAllStation();
        model.addAttribute("stationList", stationList);
        model.addAttribute("edit", false);
        return "add_route";
    }

    @ResponseBody
    @RequestMapping(value = "new-route", method = RequestMethod.POST, consumes = "application/json")
    public List<RoutePointDto> saveRoute(@RequestBody RoutePointDto routePointDto) {

        RouteDto routeDto;

        try {
            routeDto = routeService.getRouteDtoByName(routePointDto.getRouteName());
        } catch (RouteDoesNotExistException e) {
            String message = "Route with name \"" + routePointDto.getRouteName() + "\" does not exist, creating...";
            LOG.warn(message);

            routeDto = new RouteDto();
            routeDto.setName(routePointDto.getRouteName());
            try {
                routeService.addRoute(routeDto);
            } catch (RouteWithSuchNameExistException e1) {
                String msg = "Route with name \"" + routePointDto.getRouteName() + "\" already exist";
                LOG.warn(msg);
            }

            message = "Route \"" + routeDto.getName() + "\" creating successfully";
            LOG.warn(message);
        }

        try {
            routeService.addRoutePoint(routePointDto);
        } catch (StationDoesNotExistException e) {
            String message = "Station with name \"" + routePointDto.getStationName() + "\" does not exist";
            LOG.warn(message);
        } catch (RouteDoesNotExistException e) {
            String message = "Route with name \"" + routePointDto.getRouteName() + "\" does not exist";
            LOG.warn(message);
        }

        return routeService.getRoutePointsDtoList(routeDto);
    }

    @RequestMapping(value = {"edit-{name}-route"}, method = RequestMethod.GET)
    public String editRoute(@PathVariable String name, ModelMap model) {

        RouteDto routeDto = null;
        List<RoutePointDto> routePointList = null;

        try {
            routeDto = routeService.getRouteDtoByName(name);
            routePointList = routeService.getRoutePointsDtoList(routeDto);
        } catch (RouteDoesNotExistException e) {
            String message = "Route with name \"" + name + "\" does not exist";
            LOG.warn(message, e);
        }

        List<Station> stationList = stationService.getAllStation();
        model.addAttribute("stationList", stationList);
        model.addAttribute("route", routeDto);
        model.addAttribute("routePointList", routePointList);
        model.addAttribute("edit", true);
        return "add_route";
    }

    // TODO вопрос по пересылаемому объекту
    @RequestMapping(value = {"edit-{name}-route"}, method = RequestMethod.POST)
    public String updateRoute(@PathVariable String name, RouteDto routeDto, ModelMap model) {

        routeService.updateRoute(routeDto);

        model.addAttribute("success", "Route " + routeDto.getName() + " updated successfully");
        return "route-success";
    }

    @RequestMapping(value = {"delete-{name}-route"}, method = RequestMethod.GET)
    public String deleteRoute(@PathVariable String name) {
        RouteDto routeDto = null;
        try {
            routeDto = routeService.getRouteDtoByName(name);
        } catch (RouteDoesNotExistException e) {
            String message = "Route with name \"" + name + "\" does not exist";
            LOG.warn(message);
        }
        routeService.deleteRoute(routeDto);
        return "redirect:/admin-all-routes";
    }
}