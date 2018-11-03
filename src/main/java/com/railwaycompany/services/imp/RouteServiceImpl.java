package com.railwaycompany.services.imp;

import com.railwaycompany.dao.api.RouteDao;
import com.railwaycompany.dao.api.RoutePointDao;
import com.railwaycompany.dao.api.StationDao;
import com.railwaycompany.dto.RouteDto;
import com.railwaycompany.dto.RoutePointDto;
import com.railwaycompany.entities.Route;
import com.railwaycompany.entities.RoutePoint;
import com.railwaycompany.entities.Station;
import com.railwaycompany.services.api.RouteService;
import com.railwaycompany.services.exceptions.RouteDoesNotExist;
import com.railwaycompany.services.exceptions.RoutePointsForThisRouteDoesNotExist;
import com.railwaycompany.services.exceptions.RoutePointsForThisRouteExist;
import com.railwaycompany.services.exceptions.RouteWithSuchNameExistException;
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

    private RoutePointDto constructRoutePointDto(RoutePoint routePoint) {
        RoutePointDto routePointDto = new RoutePointDto();
        routePointDto.setId(routePoint.getId());
        routePointDto.setRoute(routePoint.getRoute());
        routePointDto.setStation(routePoint.getStation());
        routePointDto.setDateArrival(routePoint.getDateArrival());
        routePointDto.setDateDeparture(routePoint.getDateDeparture());
        return routePointDto;
    }

    @Override
    public void addRoute(String name) throws RouteWithSuchNameExistException {
        Route route = routeDao.getRouteByName(name);
        if (route == null) {
            route = new Route();
            route.setName(name);
            routeDao.create(route);
        } else {
            String message = "Route \"" + name + "\" is already exist";
            throw new RouteWithSuchNameExistException(message);
        }
    }

    @Override
    public void addRoutePointsForRouteByRouteId(long id, ArrayList<Station> stationList,
                                                ArrayList<Date> dateArrivalList, ArrayList<Date> dateDepartureList) throws RoutePointsForThisRouteExist {
        Route route = routeDao.read(id);
        List<RoutePoint> routePointList = routePointDao.getRoutePointsByRouteId(id);
        if (routePointList == null) {
            RoutePoint routePoint = new RoutePoint();
            for (int i = 0; i < stationList.size(); i++) {
                routePoint = new RoutePoint();
                routePoint.setRoute(route);
                routePoint.setStation(stationList.get(i));
                routePoint.setDateArrival(dateArrivalList.get(i));
                routePoint.setDateDeparture(dateDepartureList.get(i));
                routePointDao.create(routePoint);
            }
        } else {
            String message = "Route \"" + route.getName() + "\" already contains a schedule";
            throw new RoutePointsForThisRouteExist(message);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public RouteDto getRouteDtoById(long id) throws RouteDoesNotExist {
        Route route = routeDao.read(id);
        RouteDto routeDto = null;
        if (route != null) {
            routeDto = new RouteDto();
            routeDto.setId(route.getId());
            routeDto.setName(route.getName());
        } else {
            String message = "Route with id " + id + " does not exist";
            throw new RouteDoesNotExist(message);
        }
        return routeDto;
    }

    @Transactional(readOnly = true)
    @Override
    public RouteDto getRouteDtoByName(String name) throws RouteDoesNotExist {
        Route route = routeDao.getRouteByName(name);
        RouteDto routeDto = null;
        if (route != null) {
            routeDto = new RouteDto();
            routeDto.setId(route.getId());
            routeDto.setName(route.getName());
        } else {
            String message = "Route with name \"" + name + "\" does not exist";
            throw new RouteDoesNotExist(message);
        }
        return routeDto;
    }

    @Transactional(readOnly = true)
    @Override
    public List<RouteDto> getAllRoutesDto() {
        List<RouteDto> routeDtoList = null;
        List<Route> routeList = routeDao.readAll();
        if (routeList != null && !routeList.isEmpty()) {
            routeDtoList = new ArrayList<>();
            for (Route route : routeList) {
                RouteDto routeDto = new RouteDto();
                routeDto.setId(route.getId());
                routeDto.setName(route.getName());
                routeDtoList.add(routeDto);
            }
        }
        return routeDtoList;
    }

    @Transactional(readOnly = true)
    @Override
    public List<RoutePointDto> getRoutePointsDtoByRouteId(long id) throws RoutePointsForThisRouteDoesNotExist {
        List<RoutePointDto> routePointsDtoList = null;
        List<RoutePoint> routePointsList = routePointDao.getRoutePointsByRouteId(id);
        if (routePointsList != null && !routePointsList.isEmpty()) {
            routePointsDtoList = new ArrayList<>();
            for (RoutePoint routePoint : routePointsList) {
                routePointsDtoList.add(constructRoutePointDto(routePoint));
            }
        } else {
            String message = "Route with id = " + id + " does not have route points";
            throw new RoutePointsForThisRouteDoesNotExist(message);
        }
        return routePointsDtoList;
    }

    @Transactional(readOnly = true)
    @Override
    public List<RoutePointDto> getAllRoutePointsDto() {
        List<RoutePointDto> routePointDtoList = null;
        List<RoutePoint> routePointList = routePointDao.readAll();
        if (routePointList != null && !routePointList.isEmpty()) {
            routePointDtoList = new ArrayList<>();
            for (RoutePoint routePoint : routePointList) {
                RoutePointDto routePointDto = constructRoutePointDto(routePoint);
                routePointDtoList.add(routePointDto);
            }
        }
        return routePointDtoList;
    }

    @Transactional(readOnly = true)
    @Override
    public List<RouteDto> findRouteByStationsName(String stationFromName, String stationToName) throws RoutePointsForThisRouteDoesNotExist {

        List<RouteDto> routeDtoList = getAllRoutesDto();

        List<RouteDto> result = null;

        if (routeDtoList != null && !routeDtoList.isEmpty()) {
            result = new ArrayList<>();

            for (RouteDto routeDto : routeDtoList) {
                List<RoutePointDto> routePointDtoList = getRoutePointsDtoByRouteId(routeDto.getId());
                int countStations = 0;
                for (RoutePointDto routePointDto : routePointDtoList) {
                    if (routePointDto.getStation().getName().equals(stationFromName) &&
                            routePointDto.getStation().getName().equals(stationToName)) {
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
    public List<RouteDto> findRouteByStationsNameAndDate(String stationFromName, String stationToName, Date date)
            throws RouteDoesNotExist, RoutePointsForThisRouteDoesNotExist {

        List<RouteDto> routeDtoList = getAllRoutesDto();

        List<RouteDto> result = null;

        if (routeDtoList != null && !routeDtoList.isEmpty()) {
            result = new ArrayList<>();

            for (RouteDto routeDto : routeDtoList) {
                List<RoutePointDto> routePointDtoList = getRoutePointsDtoByRouteId(routeDto.getId());
                int countStations = 0;
                for (RoutePointDto routePointDto : routePointDtoList) {
                    if (routePointDto.getStation().getName().equals(stationFromName) &&
                            routePointDto.getStation().getName().equals(stationToName) &&
                            routePointDto.getDateDeparture().getTime() == date.getTime()) {
                        countStations++;
                    }
                }
                if (countStations == 2) result.add(routeDto);
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