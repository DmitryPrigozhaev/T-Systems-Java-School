package com.railwaycompany.services.api;

import com.railwaycompany.dto.RoutePointDto;
import com.railwaycompany.dto.StationDto;
import com.railwaycompany.services.exceptions.StationDoesNotExistException;
import com.railwaycompany.services.exceptions.StationWithSuchNameExistException;

import java.util.Date;
import java.util.List;

/**
 * Interface of {@link com.railwaycompany.entities.Station} service
 *
 * @author Dmitry Prigozhaev
 * @version 1.0
 */

public interface StationService {

    void addStation(StationDto stationDto) throws StationWithSuchNameExistException;

    StationDto getStationByName(String name) throws StationDoesNotExistException;

    List<StationDto> getAllStation();

    List<RoutePointDto> getStationScheduleByStationName(String name, Date date);

    void updateStation(StationDto stationDto) throws StationWithSuchNameExistException;

    void deleteStation(StationDto stationDto);

    boolean isExist(String stationName);

}