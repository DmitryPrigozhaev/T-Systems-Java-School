package com.railwaycompany.services.imp;

import com.railwaycompany.dao.api.RoutePointDao;
import com.railwaycompany.dao.api.StationDao;
import com.railwaycompany.dto.StationDto;
import com.railwaycompany.entities.RoutePoint;
import com.railwaycompany.entities.Station;
import com.railwaycompany.services.api.StationService;
import com.railwaycompany.services.exceptions.StationWithSuchNameDoesNotExistException;
import com.railwaycompany.services.exceptions.StationWithSuchNameExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("stationService")
@Transactional(readOnly = false)
public class StationServiceImpl implements StationService {

    @Autowired
    private StationDao stationDao;

    @Autowired
    private RoutePointDao routePointDao;

    @Override
    public void addStation(String name) throws StationWithSuchNameExistException {
        Station station = stationDao.getStationByName(name);
        if (station == null) {
            station = new Station();
            station.setName(name);
            stationDao.create(station);
        } else {
            String message = "Station \"" + name + "\" is already exist!";
            throw new StationWithSuchNameExistException(message);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public StationDto getStationDto(String name) throws StationWithSuchNameDoesNotExistException {
        Station station = stationDao.getStationByName(name);
        StationDto stationDto = null;
        if (station != null) {
            stationDto = new StationDto();
            stationDto.setId(station.getId());
            stationDto.setStationName(station.getName());
        } else {
            String message = "Station with name " + name + " does not exist";
            throw new StationWithSuchNameDoesNotExistException(message);
        }
        return stationDto;
    }

    @Transactional(readOnly = true)
    @Override
    public List<StationDto> getAllStationDto() {
        List<StationDto> stationDtoList = null;
        List<Station> stationList = stationDao.readAll();
        if (stationList != null && !stationList.isEmpty()) {
            stationDtoList = new ArrayList<>();
            for (Station station : stationList) {
                StationDto stationDto = new StationDto();
                stationDto.setId(station.getId());
                stationDto.setStationName(station.getName());
                stationDtoList.add(stationDto);
            }
        }
        return stationDtoList;
    }

    @Override
    public List<RoutePoint> getStationScheduleByStationId(long stationId) {
        return routePointDao.getStationScheduleByStationId(stationId);
    }

    @Override
    public List<RoutePoint> getStationScheduleByStationName(String name) {
        return routePointDao.getStationScheduleByStationName(name);
    }

    @Override
    public void updateStation(Station station) {
        stationDao.update(station);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean isExist(String stationName) {
        return stationDao.getStationByName(stationName) != null;
    }

}