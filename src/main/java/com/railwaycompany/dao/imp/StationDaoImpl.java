package com.railwaycompany.dao.imp;

import com.railwaycompany.dao.api.StationDao;
import com.railwaycompany.entities.Station;
import com.railwaycompany.exceptions.StationWithSuchNameExistException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class StationDaoImpl extends AbstractGenericDao<Station> implements StationDao {

    private static final Logger LOG = Logger.getLogger(StationDaoImpl.class.getName());

    private static final String GET_STATION_BY_NAME = "SELECT s FROM Station s WHERE s.stationName = :STATION_NAME";

    @Override
    public void addStation(String name, boolean status) throws StationWithSuchNameExistException {
        if (getStationByName(name) == null) {
            Station station = new Station();
            station.setStationName(name);
            station.setStationStatus(status);
            create(station);
        } else {
            String message = "Station \"" + name + "\" already exist!";
            throw new StationWithSuchNameExistException(message);
        }
    }

    @Override
    public Station getStationByName(String stationName) {
        Query query = getCurrentSession().createQuery(GET_STATION_BY_NAME);
        query.setParameter("STATION_NAME", stationName);

        Station station = null;
        try {
            station = (Station) query.getSingleResult();
        } catch (Exception e) {
            LOG.warn(e);
        }
        return station;
    }

    @Override
    public boolean getStationStatusByName(String name) {
        Station station = getStationByName(name);
        return station.getStationStatus();
    }

    @Override
    public void setStationStatusByName(String name, boolean status) {
        Station station = getStationByName(name);
        station.setStationStatus(status);
    }
}
