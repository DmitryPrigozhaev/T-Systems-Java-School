package com.railwaycompany.controllers;

import com.railwaycompany.entities.Route;
import com.railwaycompany.entities.RoutePoint;
import com.railwaycompany.entities.Station;
import com.railwaycompany.services.api.RouteService;
import com.railwaycompany.services.api.StationService;
import com.railwaycompany.services.exceptions.RouteWithSuchNameExistException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        List<Route> routeList = routeService.getAllRoutes();
        List<Station> stationList = stationService.getAllStation();
        model.addAttribute("routeList", routeList);
        model.addAttribute("stationList", stationList);
        return "add_route";
    }

    @RequestMapping(value = "new-route", method = RequestMethod.POST)
    public String saveRoute(@ModelAttribute("route") Route route,
                            @ModelAttribute("stationId") long[] stationIdArray,
                            @ModelAttribute("dateDeparture") String[] dateDepartureArray,
                            @ModelAttribute("dateArrival") String[] dateArrivalArray,
                            ModelMap model) {

        try {
            routeService.addRoute(route);
        } catch (RouteWithSuchNameExistException e) {
            String message = "Route with name = " + route.getName() + " already exist";
            LOG.warn(message, e);
        }

        routeService.addRoutePointsForRouteByRouteId(route.getId(), stationIdArray, dateDepartureArray, dateArrivalArray);

        model.addAttribute("success", "Route " + route.getName() + " registered successfully");
        return "route-success";
    }
}