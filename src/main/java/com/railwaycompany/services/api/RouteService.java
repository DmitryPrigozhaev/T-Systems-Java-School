package com.railwaycompany.services.api;

import com.railwaycompany.dto.RouteDto;
import com.railwaycompany.dto.RoutePointDto;
import com.railwaycompany.entities.Route;
import com.railwaycompany.entities.RoutePoint;
import com.railwaycompany.entities.Station;
import com.railwaycompany.services.exceptions.RouteDoesNotExist;
import com.railwaycompany.services.exceptions.RoutePointsForThisRouteDoesNotExist;
import com.railwaycompany.services.exceptions.RoutePointsForThisRouteExist;
import com.railwaycompany.services.exceptions.RouteWithSuchNameExistException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface RouteService {

    void addRoute(String name) throws RouteWithSuchNameExistException;

    void addRoutePointsForRouteByRouteId(long id, ArrayList<Station> stationList,
                                         ArrayList<Date> dateArrivalList, ArrayList<Date> dateDepartureList) throws RoutePointsForThisRouteExist;

    RouteDto getRouteDtoById(long id) throws RouteDoesNotExist;

    RouteDto getRouteDtoByName(String name) throws RouteDoesNotExist;

    List<RouteDto> getAllRoutesDto();

    List<RoutePointDto> getRoutePointsDtoByRouteId(long id) throws RoutePointsForThisRouteDoesNotExist;

    List<RoutePointDto> getAllRoutePointsDto();

    List<RouteDto> findRouteByStationsName(String stationFrom, String stationTo) throws RoutePointsForThisRouteDoesNotExist;

    List<RouteDto> findRouteByStationsNameAndDate(String stationFrom, String stationTo, Date date) throws RoutePointsForThisRouteDoesNotExist, RouteDoesNotExist;

    void updateRoute(Route route);

    void updateRoutePoint(RoutePoint routePoint);

    void deleteRoute(Route route);

    void deleteRoutePoint(RoutePoint routePoint);
}
