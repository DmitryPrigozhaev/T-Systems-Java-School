package com.railwaycompany.services.api;

import com.railwaycompany.dto.StationDto;
import com.railwaycompany.entities.RoutePoint;
import com.railwaycompany.entities.Station;
import com.railwaycompany.services.exceptions.StationWithSuchNameDoesNotExistException;
import com.railwaycompany.services.exceptions.StationWithSuchNameExistException;

import java.util.List;

public interface StationService {

    void addStation(String name) throws StationWithSuchNameExistException;

    StationDto getStationDto(String name) throws StationWithSuchNameDoesNotExistException;

    List<StationDto> getAllStationDto();

    List<RoutePoint> getStationScheduleByStationId(long stationId);

    List<RoutePoint> getStationScheduleByStationName(String name);

    void updateStation(Station station);

    boolean isExist(String stationName);

}