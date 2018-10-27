package com.railwaycompany.dao.api;

import com.railwaycompany.entities.Station;

public interface StationDao extends GenericDao<Station> {

    Station getStationByName(String name);

    boolean getStationStatus(String name);

}
