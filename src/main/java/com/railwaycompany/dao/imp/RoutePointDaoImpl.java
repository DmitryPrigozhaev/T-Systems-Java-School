package com.railwaycompany.dao.imp;

import com.railwaycompany.dao.api.RoutePointDao;
import com.railwaycompany.entities.RoutePoint;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class RoutePointDaoImpl extends AbstractGenericDao<RoutePoint> implements RoutePointDao {

    private static final Logger LOG = Logger.getLogger(RoutePointDaoImpl.class.getName());

    private static final String GET_SCHEDULE_BY_STATION_ID = "SELECT rp FROM RoutePoint rp WHERE rp.station.id = :stationId";

    private static final String GET_SCHEDULE_BY_STATION_NAME = "SELECT rp FROM RoutePoint rp WHERE rp.station.name = :stationName";

    private static final String GET_ROUTE_POINTS_BY_ROUTE_ID = "SELECT rp FROM RoutePoint rp WHERE rp.route.id = :routeId";

    private static final String GET_ROUTE_POINTS_BY_ROUTE_NAME = "SELECT rp FROM RoutePoint rp WHERE rp.route.name = :routeName";

    @Override
    public List<RoutePoint> getRoutePointsByStationId(long stationId) {
        Query query = getCurrentSession().createQuery(GET_SCHEDULE_BY_STATION_ID);
        query.setParameter("stationId", stationId);

        List<RoutePoint> stationSchedule = null;
        try {
            List resultList = query.getResultList();
            stationSchedule = new ArrayList<>();
            for (Object o : resultList) {
                stationSchedule.add((RoutePoint) o);
            }
        } catch (NoResultException e) {
            LOG.warn("No schedule was found for stationId: " + stationId);
        }
        return stationSchedule;
    }

    @Override
    public List<RoutePoint> getRoutePointsByStationName(String stationName) {
        Query query = getCurrentSession().createQuery(GET_SCHEDULE_BY_STATION_NAME);
        query.setParameter("stationName", stationName);

        List<RoutePoint> stationSchedule = null;
        try {
            List resultList = query.getResultList();
            stationSchedule = new ArrayList<>();
            for (Object o : resultList) {
                stationSchedule.add((RoutePoint) o);
            }
        } catch (NoResultException e) {
            LOG.warn("No schedule was found for stationName: " + stationName);
        }
        return stationSchedule;
    }

    @Override
    public List<RoutePoint> getRoutePointsByRouteId(long routeId) {
        Query query = getCurrentSession().createQuery(GET_ROUTE_POINTS_BY_ROUTE_ID);
        query.setParameter("routeId", routeId);

        List<RoutePoint> routePointList = null;
        List resultList = query.getResultList();
        if (resultList != null && !resultList.isEmpty()) {
            routePointList = new ArrayList<>();
            for (Object o : resultList) {
                routePointList.add((RoutePoint) o);
            }
        }
        return routePointList;
    }

    @Override
    public List<RoutePoint> getRoutePointsByRouteName(String routeName) {
        Query query = getCurrentSession().createQuery(GET_ROUTE_POINTS_BY_ROUTE_NAME);
        query.setParameter("routeName", routeName);

        List<RoutePoint> routePointList = null;
        List resultList = query.getResultList();
        if (resultList != null && !resultList.isEmpty()) {
            routePointList = new ArrayList<>();
            for (Object o : resultList) {
                routePointList.add((RoutePoint) o);
            }
        }
        return routePointList;
    }
}