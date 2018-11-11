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

//    @RequestMapping(value = "get-route-points", method = RequestMethod.GET)
//    public ModelMap getRoutePointsForRoute() {
//        JsonArray array = new JsonArray();
//
//    }

    @RequestMapping(value = "new-route", method = RequestMethod.GET)
    public String newRoute(ModelMap model) {
        List<Route> routeList = routeService.getAllRoutes();
        List<Station> stationList = stationService.getAllStation();
        model.addAttribute("routeList", routeList);
        model.addAttribute("stationList", stationList);
        model.addAttribute("edit", false);
        return "add_route";
    }

    @ResponseBody
    @RequestMapping(value = "new-route", method = RequestMethod.POST)
    public String saveRoute(@RequestBody RouteDto routeDto,
                            ModelMap modelMap) {

        Route route = null;

        try {
            route = routeService.getRouteByName(routeDto.getRouteName());
        } catch (RouteDoesNotExist e) {
            String info = "Route with name \"" + routeDto.getRouteName() + "\" does not exist, creating...";
            LOG.warn(info);

            route = new Route();
            route.setName(routeDto.getRouteName());
            routeService.addRoute(route);

            String message = "Route with name \"" + routeDto.getRouteName() + "\" created successfully";
            LOG.warn(message);
        }

        RoutePoint routePoint = new RoutePoint();
        try {
            routePoint.setRoute(route);
            routePoint.setStation(stationService.getStationById(routeDto.getStation()));

            if (routeDto.getDateArrival().equals("")) {
                routeDto.setDateArrival(null);
            } else {
                routePoint.setDateArrival(DateConverter.convertDatetime(routeDto.getDateArrival()));
            }

            if (routeDto.getDateDeparture().equals("")) {
                routeDto.setDateDeparture(null);
            } else {
                routePoint.setDateDeparture(DateConverter.convertDatetime(routeDto.getDateDeparture()));
            }
        } catch (StationDoesNotExistException e) {
            String message = "Station with id = " + routeDto.getStation() + " does not exist";
            LOG.warn(message, e);
        }

        routeService.addRoutePoint(routePoint);

//        List<RoutePoint> routePointList = null;
//        try {
//            routePointList = routeService.getRoutePoints(route);
//        } catch (RoutePointsForThisRouteDoesNotExist e) {
//            String message = "Route points for route with name \"" + route.getName() + "\" does not exist";
//            LOG.warn(message);
//        }
//
//        modelMap.addAttribute("routePointList", routePointList);

        return "redirect:/new-route";
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