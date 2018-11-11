package com.railwaycompany.services.imp;

import com.railwaycompany.dao.api.RouteDao;
import com.railwaycompany.dao.api.RoutePointDao;
import com.railwaycompany.dao.api.StationDao;
import com.railwaycompany.entities.Route;
import com.railwaycompany.entities.RoutePoint;
import com.railwaycompany.entities.Station;
import com.railwaycompany.services.api.RouteService;
import com.railwaycompany.services.exceptions.RouteDoesNotExist;
import com.railwaycompany.services.exceptions.RoutePointsForThisRouteDoesNotExist;
import com.railwaycompany.utils.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("routeService")
@Transactional(readOnly = false)
public class RouteServiceImpl implements RouteService {

    @Autowired
    RouteDao routeDao;

    @Autowired
    RoutePointDao routePointDao;

    @Autowired
    StationDao stationDao;

    @Override
    public void addRoute(String name) /*throws RouteWithSuchNameExistException*/ {
        /*Route route = routeDao.getRouteByName(name);
        if (route == null) {*/
            Route route = new Route();
            route.setName(name);
            routeDao.create(route);
        /*} else {
            String message = "Route \"" + name + "\" is already exist";
            throw new RouteWithSuchNameExistException(message);
        }*/
    }

    @Override
    public void addRoute(Route route) {
        routeDao.create(route);
    }

    @Override
    public void addRoutePoint(RoutePoint routePoint) {
        routePointDao.create(routePoint);
    }

    @Override
    public void addRoutePointsForRouteByRouteId(long id, List<Station> stationList,
                                                List<Date> dateArrivalList, List<Date> dateDepartureList) {
        Route route = routeDao.read(id);
        RoutePoint routePoint = new RoutePoint();
        for (int i = 0; i < stationList.size(); i++) {
            routePoint.setRoute(route);
            routePoint.setStation(stationList.get(i));
            routePoint.setDateArrival(dateArrivalList.get(i));
            routePoint.setDateDeparture(dateDepartureList.get(i));
            routePointDao.create(routePoint);
        }
    }

    @Override
    public void addRoutePointsForRouteByRouteId(long id, long[] stationId, String[] dateArrivalArray, String[] dateDepartureArray) {
        Route route = routeDao.read(id);
        RoutePoint routePoint = new RoutePoint();
        for (int i = 0; i < stationId.length; i++) {
            routePoint.setRoute(route);
            routePoint.setStation(stationDao.read(stationId[i]));
            routePoint.setDateArrival(DateConverter.convertDate(dateArrivalArray[i]));
            routePoint.setDateDeparture(DateConverter.convertDate(dateDepartureArray[i]));
            routePointDao.create(routePoint);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Route getRouteById(long id) throws RouteDoesNotExist {
        Route route = routeDao.read(id);
        if (route == null) {
            String message = "Route with id " + id + " does not exist";
            throw new RouteDoesNotExist(message);
        }
        return route;
    }

    @Transactional(readOnly = true)
    @Override
    public Route getRouteByName(String name) throws RouteDoesNotExist {
        Route route = routeDao.getRouteByName(name);
        if (route == null) {
            String message = "Route with name \"" + name + "\" does not exist";
            throw new RouteDoesNotExist(message);
        }
        return route;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Route> getAllRoutes() {
        return routeDao.readAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<RoutePoint> getRoutePointsByRouteId(long id) throws RoutePointsForThisRouteDoesNotExist {
        List<RoutePoint> routePointsList = routePointDao.getRoutePointsByRouteId(id);
        if (routePointsList == null || routePointsList.isEmpty()) {
            String message = "Route with id = " + id + " does not have route points";
            throw new RoutePointsForThisRouteDoesNotExist(message);
        }
        return routePointsList;
    }

    @Transactional(readOnly = true)
    @Override
    public List<RoutePoint> getRoutePoints(Route route) {
        return routeDao.getRoutePoints(route);
    }

    @Transactional(readOnly = true)
    @Override
    public List<RoutePoint> getAllRoutePoints() {
        return routePointDao.readAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Route> findRouteByStationsName(String stationFromName, String stationToName) throws RoutePointsForThisRouteDoesNotExist {

        List<Route> routeList = getAllRoutes();

        List<Route> result = null;

        if (routeList != null && !routeList.isEmpty()) {
            result = new ArrayList<>();

            for (Route route : routeList) {
                List<RoutePoint> routePointList = getRoutePointsByRouteId(route.getId());
                int countStations = 0;
                for (RoutePoint routePoint : routePointList) {
                    if (routePoint.getStation().getName().equals(stationFromName) &&
                            routePoint.getStation().getName().equals(stationToName)) {
                        countStations++;
                    }
                }
                if (countStations == 2) result.add(route);
            }
        }
        return result;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Route> findRouteByStationsNameAndDate(String stationFromName, String stationToName, Date date)
            throws RouteDoesNotExist, RoutePointsForThisRouteDoesNotExist {

        List<Route> routeList = getAllRoutes();

        List<Route> result = null;

        if (routeList != null && !routeList.isEmpty()) {
            result = new ArrayList<>();

            for (Route route : routeList) {
                List<RoutePoint> routePointList = getRoutePointsByRouteId(route.getId());
                int countStations = 0;
                for (RoutePoint routePoint : routePointList) {
                    if (routePoint.getStation().getName().equals(stationFromName) &&
                            routePoint.getStation().getName().equals(stationToName) &&
                            routePoint.getDateDeparture().getTime() == date.getTime()) {
                        countStations++;
                    }
                }
                if (countStations == 2) result.add(route);
            }
        } else {
            String message = "Route from station \"" + stationFromName + "\" and station \"" + stationToName + "\" does not exist";
            throw new RouteDoesNotExist(message);
        }
        return result;
    }

    @Override
    public void updateRoute(Route route) {
        routeDao.update(route);
    }

    @Override
    public void updateRoutePoint(RoutePoint routePoint) {
        routePointDao.update(routePoint);
    }

    @Override
    public void deleteRoute(Route route) {
        routeDao.delete(route);
    }

    @Override
    public void deleteRoutePoint(RoutePoint routePoint) {
        routePointDao.delete(routePoint);
    }
}