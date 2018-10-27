package com.railwaycompany.services.api;

import com.railwaycompany.entities.Station;

import java.util.List;

public interface StationService {

    void addStation(String name, boolean status);

    Station getStation(String name);

    List<Station> getAllStation();

    void updateStation(Station station);

    boolean isActiveStation(String name);
}
