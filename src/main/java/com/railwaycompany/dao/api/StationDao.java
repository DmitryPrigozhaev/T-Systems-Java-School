package com.railwaycompany.dao.api;

import com.railwaycompany.entities.Station;
import com.railwaycompany.exceptions.StationWithSuchNameExistException;

public interface StationDao extends GenericDao<Station> {

    void addStation(String name, boolean status) throws StationWithSuchNameExistException;

    Station getStationByName(String name);

    boolean getStationStatusByName(String name);

    void setStationStatusByName(String name, boolean status);

}
