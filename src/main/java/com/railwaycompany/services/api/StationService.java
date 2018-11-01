package com.railwaycompany.services.api;

import com.railwaycompany.dto.StationDto;
import com.railwaycompany.entities.Station;
import com.railwaycompany.services.exceptions.StationWithSuchNameExistException;

import java.util.List;

public interface StationService {

    void addStation(String name) throws StationWithSuchNameExistException;

    StationDto getStationDto(String name);

    List<StationDto> getAllStationDto();

    void updateStation(Station station);

    boolean isExist(String stationName);

}