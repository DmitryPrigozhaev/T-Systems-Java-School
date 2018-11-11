package com.railwaycompany.controllers;

import com.railwaycompany.dto.RouteDto;
import com.railwaycompany.entities.Route;
import com.railwaycompany.entities.RoutePoint;
import com.railwaycompany.entities.Station;
import com.railwaycompany.services.api.RouteService;
import com.railwaycompany.services.api.StationService;
import com.railwaycompany.services.exceptions.RouteDoesNotExist;
import com.railwaycompany.services.exceptions.StationDoesNotExistException;
import com.railwaycompany.utils.DateConverter;
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
        List<Route> routeList = routeService.getAllRoutes();
        List<RoutePoint> routePointList = routeService.getAllRoutePoints();
        model.addAttribute("routeList", routeList);
        model.addAttribute("routePointList", routePointList);
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
    @RequestMapping(value = "new-route", method = RequestMethod.POST)
    public List<RoutePoint> saveRoute(@RequestBody RouteDto routeDto) {

        Route route;
        RoutePoint routePoint;

        try {
            route = routeService.getRouteByName(routeDto.getRouteName());
        } catch (RouteDoesNotExist e) {
            String message = "Route with name \"" + routeDto.getRouteName() + "\" does not exist, creating...";
            LOG.warn(message);

            route = new Route();
            route.setName(routeDto.getRouteName());
            routeService.addRoute(route);

            message = "Route \"" + route.getName() + "\" creating successfully";
            LOG.warn(message);
        }

        if (route != null) {
            try {
                routePoint = new RoutePoint();
                routePoint.setRoute(route);
                routePoint.setStation(stationService.getStationByName(routeDto.getStation()));
                if (routeDto.getDateArrival().equals("")) {
                    routePoint.setDateArrival(null);
                } else {
                    routePoint.setDateArrival(DateConverter.convertDatetime(routeDto.getDateArrival()));
                }
                if (routeDto.getDateDeparture().equals("")) {
                    routePoint.setDateDeparture(null);
                } else {
                    routePoint.setDateDeparture(DateConverter.convertDatetime(routeDto.getDateDeparture()));
                }
                routeService.addRoutePoint(routePoint);
            } catch (StationDoesNotExistException e) {
                String message = "Station with name \"" + routeDto.getStation() + "\" does not exist";
                LOG.error(message, e);
            }
        }

        List<RoutePoint> routePoints = routeService.getRoutePoints(route);
        for (RoutePoint point : routePoints) {
            System.out.println(point.getId());
            System.out.println(point.getRoute().getName());
            System.out.println(point.getStation().getName());
            System.out.println(point.getDateArrival());
            System.out.println(point.getDateDeparture());
        }

        return routePoints;
    }

    @RequestMapping(value = {"edit-{name}-route"}, method = RequestMethod.GET)
    public String editRoute(@PathVariable String name, ModelMap model) {
        Route route = null;
        List<RoutePoint> routePointList = null;

        try {
            route = routeService.getRouteByName(name);
            routePointList = routeService.getRoutePoints(route);
        } catch (RouteDoesNotExist e) {
            String message = "Route with name \"" + name + "\" does not exist";
            LOG.warn(message, e);
        }

        List<Station> stationList = stationService.getAllStation();
        model.addAttribute("stationList", stationList);
        model.addAttribute("route", route);
        model.addAttribute("routePointList", routePointList);
        model.addAttribute("edit", true);
        return "add_route";
    }

    @RequestMapping(value = {"edit-{name}-route"}, method = RequestMethod.POST)
    public String updateRoute(@PathVariable String name, Route route, List<RoutePoint> list, ModelMap model) {

        routeService.updateRoute(route);

        model.addAttribute("success", "Route " + route.getName() + " updated successfully");
        return "route-success";
    }

    @RequestMapping(value = {"delete-{name}-route"}, method = RequestMethod.GET)
    public String deleteRoute(@PathVariable String name) {
        Route route = null;
        try {
            route = routeService.getRouteByName(name);
        } catch (RouteDoesNotExist e) {
            String message = "Route with name \"" + name + "\" does not exist";
            LOG.warn(message);
        }
        routeService.deleteRoute(route);
        return "redirect:/admin-all-routes";
    }
}