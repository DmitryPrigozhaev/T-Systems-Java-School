package com.railwaycompany.services.imp;

import com.railwaycompany.dao.api.RoutePointDao;
import com.railwaycompany.dao.api.StationDao;
import com.railwaycompany.dto.RoutePointDto;
import com.railwaycompany.dto.StationDto;
import com.railwaycompany.entities.RoutePoint;
import com.railwaycompany.entities.Station;
import com.railwaycompany.services.api.StationService;
import com.railwaycompany.services.exceptions.StationDoesNotExistException;
import com.railwaycompany.services.exceptions.StationWithSuchNameExistException;
import com.railwaycompany.utils.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = false)
public class StationServiceImpl implements StationService {

    @Autowired
    private StationDao stationDao;

    @Autowired
    private RoutePointDao routePointDao;

    private Station constructStation(StationDto stationDto) {
        Station station = new Station();
        station.setName(stationDto.getName());
        return station;
    }

    private StationDto constructStationDto(Station station) {
        StationDto stationDto = new StationDto();

        Set<RoutePoint> routePointSet = station.getRoutePointSet();
        List<RoutePointDto> routePointDtoList = null;
        if (routePointSet != null && !routePointSet.isEmpty()) {
            routePointDtoList = new ArrayList<>();
            for (RoutePoint routePoint : routePointSet) {
                RoutePointDto routePointDto = new RoutePointDto();
                routePointDto.setId(routePoint.getId());
                routePointDto.setRouteName(routePoint.getRoute().getName());
                routePointDto.setStationName(routePoint.getStation().getName());
                try {
                    routePointDto.setDateArrival(routePoint.getDateArrival().toString());
                } catch (NullPointerException e) {
                    routePointDto.setDateArrival("");
                }
                try {
                    routePointDto.setDateDeparture(routePoint.getDateDeparture().toString());
                } catch (NullPointerException e) {
                    routePointDto.setDateArrival("");
                }
                routePointDtoList.add(routePointDto);
            }
        }

        stationDto.setId(station.getId());
        stationDto.setName(station.getName());
        stationDto.setRoutePointDtoList(routePointDtoList);

        return stationDto;
    }

    @Override
    public void addStation(StationDto stationDto) throws StationWithSuchNameExistException {
        if (stationDao.getStationByName(stationDto.getName()) == null) {
            stationDao.create(constructStation(stationDto));
        } else {
            String message = "Station \"" + stationDto.getName() + "\" is already exist!";
            throw new StationWithSuchNameExistException(message);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public StationDto getStationByName(String name) throws StationDoesNotExistException {
        Station station = stationDao.getStationByName(name);
        if (station == null) {
            String message = "Station with name " + name + " does not exist";
            throw new StationDoesNotExistException(message);
        }
        return constructStationDto(station);
    }

    @Transactional(readOnly = true)
    @Override
    public List<StationDto> getAllStation() {
        List<StationDto> stationDtoList = null;
        List<Station> stationList = stationDao.readAll();
        if (stationList != null && !stationList.isEmpty()) {
            stationDtoList = new ArrayList<>();
            for (Station station : stationList) {
                stationDtoList.add(constructStationDto(station));
            }
        }
        return stationDtoList;
    }

    @Transactional(readOnly = true)
    @Override
    public List<RoutePointDto> getStationScheduleByStationName(String name, Date date) {
        Station station = stationDao.getStationByName(name);
        date = DateConverter.getDateWithoutTime(date);

        List<RoutePointDto> result = null;
        Set<RoutePoint> fullStationSchedule = station.getRoutePointSet();

        if (fullStationSchedule != null && !fullStationSchedule.isEmpty()) {
            result = new ArrayList<>();
            for (RoutePoint routePoint : fullStationSchedule) {
                if (((routePoint.getDateDeparture() != null && DateConverter.getDateWithoutTime(routePoint.getDateDeparture()).equals(date)) ||
                        (routePoint.getDateArrival() != null && DateConverter.getDateWithoutTime(routePoint.getDateArrival()).equals(date))) &&
                        routePoint.getRoute().getTrain() != null) {
                    RoutePointDto routePointDto = new RoutePointDto();
                    routePointDto.setId(routePoint.getId());
                    routePointDto.setTrainNumber(routePoint.getRoute().getTrain().getNumber());
                    routePointDto.setRouteName(routePoint.getRoute().getName());
                    routePointDto.setStationName(routePoint.getStation().getName());
                    routePointDto.setDateArrival(routePoint.getDateArrival() == null ? "" : routePoint.getDateArrival().toString());
                    routePointDto.setDateDeparture(routePoint.getDateDeparture() == null ? "" : routePoint.getDateDeparture().toString());
                    result.add(routePointDto);
                }
            }
        }
        return result;
    }

    @Override
    public void updateStation(StationDto stationDto) throws StationWithSuchNameExistException {
        Station station = stationDao.getStationByName(stationDto.getName());
        if (station == null) {
            station = stationDao.read(stationDto.getId());
            station.setName(stationDto.getName());
            stationDao.update(station);
        } else {
            String message = "Cannot update station \"" + stationDto.getName() + "\": station with such name already exist";
            throw new StationWithSuchNameExistException(message);
        }
    }

    @Override
    public void deleteStation(StationDto stationDto) {
        Station station = stationDao.read(stationDto.getId());
        stationDao.delete(station);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean isExist(String stationName) {
        return stationDao.getStationByName(stationName) != null;
    }

}