package com.railwaycompany.services.api;

import com.railwaycompany.dto.RouteDto;
import com.railwaycompany.dto.RoutePointDto;
import com.railwaycompany.dto.StationDto;
import com.railwaycompany.services.exceptions.RouteDoesNotExistException;
import com.railwaycompany.services.exceptions.RouteWithSuchNameExistException;
import com.railwaycompany.services.exceptions.StationDoesNotExistException;

import java.util.Date;
import java.util.List;

/**
 * Interface of {@link com.railwaycompany.entities.Route} service
 *
 * @author Dmitry Prigozhaev
 * @version 1.0
 */

public interface RouteService {

    void addRoute(String name) throws RouteWithSuchNameExistException;

    void addRoute(RouteDto routeDto) throws RouteWithSuchNameExistException;

    void addRoutePoint(RoutePointDto routePointDto) throws StationDoesNotExistException, RouteDoesNotExistException;

    RouteDto getRouteDtoById(long id) throws RouteDoesNotExistException;

    RouteDto getRouteDtoByName(String name) throws RouteDoesNotExistException;

    RouteDto getRouteDtoByTrainNumber(int trainNumber) throws RouteDoesNotExistException;

    List<RouteDto> getAllRoutes();

    List<RoutePointDto> getRoutePointsDtoList(RouteDto routeDto);

    List<RoutePointDto> getAllRoutePointsDtoList();

    void updateRoute(RouteDto routeDto);

    void updateRoutePoint(RoutePointDto routePointDto);

    void deleteRoute(RouteDto routeDto);

    void deleteRoutePoint(RoutePointDto routePointDto);

    List<RoutePointDto> findRoutePointsDtoByStation(StationDto stationName);

    List<RouteDto> findRouteDtoByStations(StationDto stationFrom, StationDto stationTo);

    List<RouteDto> findRouteDtoByStationsAndDate(StationDto stationFrom, StationDto stationTo, Date date);

}