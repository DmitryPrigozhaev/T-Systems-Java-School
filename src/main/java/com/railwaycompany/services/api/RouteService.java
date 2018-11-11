package com.railwaycompany.services.api;

import com.railwaycompany.entities.Route;
import com.railwaycompany.entities.RoutePoint;
import com.railwaycompany.entities.Station;
import com.railwaycompany.services.exceptions.RouteDoesNotExist;
import com.railwaycompany.services.exceptions.RoutePointsForThisRouteDoesNotExist;

import java.util.Date;
import java.util.List;

public interface RouteService {

    void addRoute(String name);

    void addRoute(Route route);

    void addRoutePoint(RoutePoint routePoint);

    void addRoutePointsForRouteByRouteId(long id, List<Station> stationList,
                                         List<Date> dateArrivalList, List<Date> dateDepartureList);

    void addRoutePointsForRouteByRouteId(long id, long[] stationId, String[] dateArrivalArray, String[] dateDepartureArray);

    Route getRouteById(long id) throws RouteDoesNotExist;

    Route getRouteByName(String name) throws RouteDoesNotExist;

    List<Route> getAllRoutes();

    List<RoutePoint> getRoutePointsByRouteId(long id) throws RoutePointsForThisRouteDoesNotExist;

    List<RoutePoint> getRoutePoints(Route route);

    List<RoutePoint> getAllRoutePoints();

    List<Route> findRouteByStationsName(String stationFrom, String stationTo) throws RoutePointsForThisRouteDoesNotExist;

    List<Route> findRouteByStationsNameAndDate(String stationFrom, String stationTo, Date date) throws RoutePointsForThisRouteDoesNotExist, RouteDoesNotExist;

    void updateRoute(Route route);

    void updateRoutePoint(RoutePoint routePoint);

    void deleteRoute(Route route);

    void deleteRoutePoint(RoutePoint routePoint);
}
