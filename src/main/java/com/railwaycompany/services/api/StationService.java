package com.railwaycompany.services.api;

import com.railwaycompany.entities.RoutePoint;
import com.railwaycompany.entities.Station;
import com.railwaycompany.services.exceptions.StationDoesNotExistException;
import com.railwaycompany.services.exceptions.StationWithSuchNameExistException;

import java.util.List;

public interface StationService {

    void addStation(Station station) throws StationWithSuchNameExistException;

    Station getStationByName(String name) throws StationDoesNotExistException;

    Station getStationById(long id) throws StationDoesNotExistException;

    List<Station> getAllStation();

    List<RoutePoint> getStationScheduleByStationId(long stationId);

    List<RoutePoint> getStationScheduleByStationName(String name);

    void updateStation(Station station) throws StationWithSuchNameExistException;

    void deleteStation(Station station);

    boolean isExist(String stationName);

}