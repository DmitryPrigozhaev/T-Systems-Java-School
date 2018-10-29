package com.railwaycompany.services.api;

import com.railwaycompany.entities.Station;
import com.railwaycompany.exceptions.StationWithSuchNameExistException;

import java.util.List;

public interface StationService {

    void addStation(String name, boolean status) throws StationWithSuchNameExistException;

    Station getStation(String name);

    List<Station> getAllStation();

    void updateStation(Station station);

    void setStationStatus(String name, boolean status);

    boolean isActiveStation(String name);
}
