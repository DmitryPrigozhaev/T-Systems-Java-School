package com.railwaycompany.services.imp;

import com.railwaycompany.dao.api.StationDao;
import com.railwaycompany.dto.StationDto;
import com.railwaycompany.entities.Station;
import com.railwaycompany.services.api.StationService;
import com.railwaycompany.services.exceptions.StationWithSuchNameExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StationServiceImpl implements StationService {

    @Autowired
    private StationDao stationDao;

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

    @Override
    public StationDto getStationDto(String name) {
        Station station = stationDao.getStationByName(name);
        StationDto stationDto = null;
        if (station != null) {
            stationDto = new StationDto();
            stationDto.setStationName(station.getName());
        }
        return stationDto;
    }

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
    public void updateStation(Station station) {
        stationDao.update(station);
    }

    @Override
    public boolean isExist(String stationName) {
        return stationDao.getStationByName(stationName) != null;
    }

}