package com.railwaycompany.services.api;

import com.railwaycompany.dto.RouteDto;
import com.railwaycompany.dto.RoutePointDto;
import com.railwaycompany.entities.Station;
import com.railwaycompany.services.exceptions.RouteDoesNotExistException;
import com.railwaycompany.services.exceptions.RouteWithSuchNameExistException;
import com.railwaycompany.services.exceptions.StationDoesNotExistException;

import java.util.Date;
import java.util.List;

public interface RouteService {

    void addRoute(String name) throws RouteWithSuchNameExistException;

    void addRoute(RouteDto routeDto) throws RouteWithSuchNameExistException;

    void addRoutePoint(RoutePointDto routePointDto) throws StationDoesNotExistException, RouteDoesNotExistException;

    RouteDto getRouteDtoById(long id) throws RouteDoesNotExistException;

    RouteDto getRouteDtoByName(String name) throws RouteDoesNotExistException;

    List<RouteDto> getAllRoutes();

    List<RoutePointDto> getRoutePointsDtoList(RouteDto routeDto);

    List<RoutePointDto> getAllRoutePointsDtoList();

    void updateRoute(RouteDto routeDto);

    void updateRoutePoint(RoutePointDto routePointDto);

    void deleteRoute(RouteDto routeDto);

    void deleteRoutePoint(RoutePointDto routePointDto);

    List<RoutePointDto> findRoutePointsDtoByStation(Station stationName);

    List<RouteDto> findRouteDtoByStations(Station stationFrom, Station stationTo);

    List<RouteDto> findRouteDtoByStationsAndDate(Station stationFrom, Station stationTo, Date date);

}