package com.railwaycompany.services.imp;

import com.railwaycompany.dao.api.StationDao;
import com.railwaycompany.entities.Station;
import com.railwaycompany.services.api.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationServiceImpl implements StationService {

    @Autowired
    private StationDao stationDao;

    @Override
    public void addStation(String name, boolean status) {
        Station station = stationDao.getStationByName(name);
        if (station == null) {
            station = new Station();
            station.setStation_name(name);
            station.setStation_status(status);
            stationDao.create(station);
        } else {
            String message = "Station \"" + name + "\" is already exist!";
            // TODO здесь бросить исключение
        }
    }

    @Override
    public Station getStation(String name) {
        return stationDao.getStationByName(name);
    }

    @Override
    public List<Station> getAllStation() {
        return stationDao.readAll();
    }

    @Override
    public void updateStation(Station station) {
        stationDao.update(station);
    }

    @Override
    public boolean isActiveStation(String name) {
        return stationDao.getStationStatus(name);
    }
}
