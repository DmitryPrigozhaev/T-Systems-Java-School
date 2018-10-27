package com.railwaycompany.dao.imp;

import com.railwaycompany.dao.api.StationDao;
import com.railwaycompany.entities.Station;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class StationDaoImpl extends AbstractGenericDao<Station> implements StationDao {

    private static final Logger LOG = Logger.getLogger(StationDaoImpl.class.getName());

    private static final String GET_STATION_BY_NAME = "SELECT s FROM Station s WHERE s.station_name = :station_name";

    @Override
    public Station getStationByName(String station_name) {
        Query query = getCurrentSession().createQuery(GET_STATION_BY_NAME);
        query.setParameter("station_name", station_name);

        Station station = null;
        try {
            station = (Station) query.getSingleResult();
        } catch (Exception e) {
            LOG.warn(e);
        }
        return station;
    }

    @Override
    public boolean getStationStatus(String name) {
        Station station = getStationByName(name);
        return station.getStation_status();
    }
}
