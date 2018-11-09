package com.railwaycompany.services.imp;

import com.railwaycompany.dao.api.RoutePointDao;
import com.railwaycompany.dao.api.StationDao;
import com.railwaycompany.entities.RoutePoint;
import com.railwaycompany.entities.Station;
import com.railwaycompany.services.api.StationService;
import com.railwaycompany.services.exceptions.StationWithSuchNameDoesNotExistException;
import com.railwaycompany.services.exceptions.StationWithSuchNameExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false)
public class StationServiceImpl implements StationService {

    @Autowired
    private StationDao stationDao;

    @Autowired
    private RoutePointDao routePointDao;

    @Override
    public void addStation(Station station) throws StationWithSuchNameExistException {
        if (stationDao.getStationByName(station.getName()) == null) {
            stationDao.create(station);
        } else {
            String message = "Station \"" + station.getName() + "\" is already exist!";
            throw new StationWithSuchNameExistException(message);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Station getStationByName(String name) throws StationWithSuchNameDoesNotExistException {
        Station station = stationDao.getStationByName(name);

        if (station == null) {
            String message = "Station with name " + name + " does not exist";
            throw new StationWithSuchNameDoesNotExistException(message);
        }

        return station;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Station> getAllStation() {
        return stationDao.readAll();
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

    @Override
    public void deleteStation(Station station) {
        stationDao.delete(station);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean isExist(String stationName) {
        return stationDao.getStationByName(stationName) != null;
    }

}