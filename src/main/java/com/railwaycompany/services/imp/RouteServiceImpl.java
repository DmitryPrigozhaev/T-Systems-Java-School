package com.railwaycompany.services.imp;

import com.railwaycompany.dao.api.RouteDao;
import com.railwaycompany.dao.api.RoutePointDao;
import com.railwaycompany.dao.api.StationDao;
import com.railwaycompany.dto.RouteDto;
import com.railwaycompany.dto.RoutePointDto;
import com.railwaycompany.dto.StationDto;
import com.railwaycompany.entities.Route;
import com.railwaycompany.entities.RoutePoint;
import com.railwaycompany.entities.Station;
import com.railwaycompany.services.api.RouteService;
import com.railwaycompany.services.exceptions.RouteDoesNotExistException;
import com.railwaycompany.services.exceptions.RouteWithSuchNameExistException;
import com.railwaycompany.services.exceptions.StationDoesNotExistException;
import com.railwaycompany.utils.DateConverter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


@Service
@Transactional(readOnly = false)
public class RouteServiceImpl implements RouteService {

    private static final Logger LOG = Logger.getLogger(RouteServiceImpl.class.getName());

    @Autowired
    RouteDao routeDao;

    @Autowired
    RoutePointDao routePointDao;

    @Autowired
    StationDao stationDao;

    private Route constructRoute(RouteDto routeDto) {
        Route route = new Route();
        route.setName(routeDto.getName());
        return route;
    }

    private RouteDto constructRouteDto(Route route) {
        List<RoutePointDto> routePointDtoList = null;
        List<RoutePoint> routePointList = route.getRoutePointList();
        if (routePointList != null && !routePointList.isEmpty()) {
            Collections.sort(routePointList);
            routePointDtoList = new ArrayList<>();
            for (RoutePoint routePoint : routePointList) {
                routePointDtoList.add(constructRoutePointDto(routePoint));
            }
        }

        RouteDto routeDto = new RouteDto();
        routeDto.setId(route.getId());
        routeDto.setName(route.getName());
        routeDto.setRoutePointDtoList(routePointDtoList);
        return routeDto;
    }

    private RoutePoint constructRoutePoint(RoutePointDto routePointDto) throws RouteDoesNotExistException, StationDoesNotExistException {
        RoutePoint routePoint = new RoutePoint();
        Route route;
        Station station;
        Date dateDeparture = null;
        Date dateArrival = null;

        route = routeDao.getRouteByName(routePointDto.getRouteName());
        if (route == null) {
            String message = "Route with name \"" + routePointDto.getRouteName() + "\" does not exist";
            LOG.warn(message);
            throw new RouteDoesNotExistException(message);
        }
        station = stationDao.getStationByName(routePointDto.getStationName());
        if (station == null) {
            String message = "Station with name \"" + routePointDto.getStationName() + "\" does not exist";
            LOG.warn(message);
            throw new StationDoesNotExistException(message);
        }
        if (!routePointDto.getDateDeparture().equals("")) {
            dateDeparture = DateConverter.convertDatetime(routePointDto.getDateDeparture());
        }
        if (!routePointDto.getDateArrival().equals("")) {
            dateArrival = DateConverter.convertDatetime(routePointDto.getDateArrival());
        }
        routePoint.setRoute(route);
        routePoint.setStation(station);
        routePoint.setDateDeparture(dateDeparture);
        routePoint.setDateArrival(dateArrival);
        return routePoint;
    }

    private RoutePointDto constructRoutePointDto(RoutePoint routePoint) {
        RoutePointDto routePointDto = new RoutePointDto();

        routePointDto.setId(routePoint.getId());
        routePointDto.setRouteName(routePoint.getRoute().getName());
        routePointDto.setStationName(routePoint.getStation().getName());
        if (routePoint.getDateDeparture() == null) {
            routePointDto.setDateDeparture("");
        } else {
            routePointDto.setDateDeparture(routePoint.getDateDeparture().toString());
        }
        if (routePoint.getDateArrival() == null) {
            routePointDto.setDateArrival("");
        } else {
            routePointDto.setDateArrival(routePoint.getDateArrival().toString());
        }
        return routePointDto;
    }

    @Override
    public void addRoute(RouteDto routeDto) throws RouteWithSuchNameExistException {
        Route route = routeDao.getRouteByName(routeDto.getName());
        if (route == null) {
            route = constructRoute(routeDto);
            routeDao.create(route);
        } else {
            String message = "Route \"" + routeDto.getName() + "\" is already exist";
            throw new RouteWithSuchNameExistException(message);
        }
    }

    @Override
    public void addRoutePoint(RoutePointDto routePointDto) throws StationDoesNotExistException, RouteDoesNotExistException {
        RoutePoint routePoint = constructRoutePoint(routePointDto);
        routePointDao.create(routePoint);
    }

    @Transactional(readOnly = true)
    @Override
    public RouteDto getRouteDtoByName(String name) throws RouteDoesNotExistException {
        Route route = routeDao.getRouteByName(name);
        if (route == null) {
            String message = "Route with name \"" + name + "\" does not exist";
            throw new RouteDoesNotExistException(message);
        }
        return constructRouteDto(route);
    }

    @Override
    public RouteDto getRouteDtoByTrainNumber(int trainNumber) throws RouteDoesNotExistException {
        Route route = routeDao.getRouteByTrainNumber(trainNumber);
        if (route == null) {
            String message = "Route for train number \"" + trainNumber + "\" does not exist";
            throw new RouteDoesNotExistException(message);
        }
        return constructRouteDto(route);
    }

    @Transactional(readOnly = true)
    @Override
    public List<RouteDto> getAllRoutes() {
        List<RouteDto> routeDtoList = null;
        List<Route> routeList = routeDao.readAll();
        if (routeList != null && !routeList.isEmpty()) {
            routeDtoList = new ArrayList<>();
            for (Route route : routeList) {
                routeDtoList.add(constructRouteDto(route));
            }
        }
        return routeDtoList;
    }

    @Transactional(readOnly = true)
    @Override
    public List<RoutePointDto> getRoutePointsDtoList(RouteDto routeDto) {
        List<RoutePointDto> routePointDtoList = null;
        List<RoutePoint> routePointsList = routePointDao.getRoutePointsByRouteName(routeDto.getName());
        if (routePointsList != null && !routePointsList.isEmpty()) {
            routePointDtoList = new ArrayList<>();
            for (RoutePoint routePoint : routePointsList) {
                routePointDtoList.add(constructRoutePointDto(routePoint));
            }
        }
        return routePointDtoList;
    }

    @Transactional(readOnly = true)
    @Override
    public List<RoutePointDto> getAllRoutePointsDtoList() {
        List<RoutePointDto> routePointsDtoList = null;
        List<RoutePoint> routePointsList = routePointDao.readAll();
        Collections.sort(routePointsList);

        if (!routePointsList.isEmpty()) {
            routePointsDtoList = new ArrayList<>();
            for (RoutePoint routePoint : routePointsList) {
                    routePointsDtoList.add(constructRoutePointDto(routePoint));
            }
        }
        return routePointsDtoList;
    }

    @Override
    public void updateRoute(RouteDto routeDto) {
        Route route = routeDao.getRouteByName(routeDto.getName());
        routeDao.update(route);
    }

    @Override
    public void updateRoutePoint(RoutePointDto routePointDto) {
        // TODO ??????
        RoutePoint routePoint = routePointDao.read(routePointDto.getId());
        routePointDao.update(routePoint);
    }

    @Override
    public void deleteRoute(RouteDto routeDto) {
        Route route = routeDao.getRouteByName(routeDto.getName());
        routeDao.delete(route);
    }

    @Override
    public void deleteRoutePoint(RoutePointDto routePointDto) {
        RoutePoint routePoint = routePointDao.read(routePointDto.getId());
        routePointDao.delete(routePoint);
    }

    @Transactional(readOnly = true)
    @Override
    public List<RoutePointDto> findRoutePointsDtoByStation(StationDto stationDto) {

        List<RoutePointDto> routePointsDtoList = null;

        List<RoutePoint> routePointsList = routePointDao.getRoutePointsByStationName(stationDto.getName());
        if (routePointsList != null && !routePointsList.isEmpty()) {
            routePointsDtoList = new ArrayList<>();
            for (RoutePoint routePoint : routePointsList) {
                routePointsDtoList.add(constructRoutePointDto(routePoint));
            }
        }
        return routePointsDtoList;
    }

    @Transactional(readOnly = true)
    @Override
    public List<RouteDto> findRouteDtoByStations(StationDto stationFrom, StationDto stationTo) {

        List<RouteDto> result = null;

        List<RouteDto> routeDtoList = getAllRoutes();
        if (routeDtoList != null && !routeDtoList.isEmpty()) {
            result = new ArrayList<>();
            for (RouteDto routeDto : routeDtoList) {
                List<RoutePointDto> routePointsDtoList = getRoutePointsDtoList(routeDto);
                int countStations = 0;
                for (RoutePointDto routePointDto : routePointsDtoList) {
                    if ((routePointDto.getStationName().equals(stationFrom.getName()) ||
                            routePointDto.getStationName().equals(stationTo.getName()))) {
                        countStations++;
                    }
                }
                if (countStations == 2) result.add(routeDto);
            }
        }
        return result;
    }

    @Transactional(readOnly = true)
    @Override
    public List<RouteDto> findRouteDtoByStationsAndDate(StationDto stationFrom, StationDto stationTo, Date date) {
        List<RouteDto> result = null;

        List<RouteDto> routeDtoList = getAllRoutes();
        if (routeDtoList != null && !routeDtoList.isEmpty()) {
            result = new ArrayList<>();
            for (RouteDto routeDto : routeDtoList) {
                List<RoutePointDto> routePointsDtoList = getRoutePointsDtoList(routeDto);
                int countStations = 0;
                for (RoutePointDto routePointDto : routePointsDtoList) {
                    if ((routePointDto.getStationName().equals(stationFrom.getName()) ||
                            routePointDto.getStationName().equals(stationTo.getName())) &&
                            (date.equals(DateConverter.convertDate(routePointDto.getDateDeparture())) ||
                                    date.equals(DateConverter.convertDate(routePointDto.getDateArrival())))) {
                        countStations++;
                    }
                }
                if (countStations == 2) result.add(routeDto);
            }
        }
        return result;
    }
}