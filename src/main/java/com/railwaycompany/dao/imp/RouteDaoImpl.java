package com.railwaycompany.dao.imp;

import com.railwaycompany.dao.api.RouteDao;
import com.railwaycompany.entities.Route;
import org.apache.log4j.Logger;

import javax.persistence.Query;

public class RouteDaoImpl extends AbstractGenericDao<Route> implements RouteDao {

    private static final Logger LOG = Logger.getLogger(RouteDaoImpl.class.getName());

    private static final String GET_ROUTE_BY_NAME = "SELECT r FROM Route r WHERE r.name = :name";

    @Override
    public Route getRouteByName(String name) {
        Query query = getCurrentSession().createQuery(GET_ROUTE_BY_NAME);
        query.setParameter("name", name);

        Route route = null;
        try {
            route = (Route) query.getSingleResult();
        } catch (Exception e) {
            LOG.warn(e);
        }
        return route;
    }

}
