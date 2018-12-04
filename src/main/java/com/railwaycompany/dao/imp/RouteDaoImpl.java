package com.railwaycompany.dao.imp;

import com.railwaycompany.dao.api.RouteDao;
import com.railwaycompany.entities.Route;
import com.railwaycompany.entities.RoutePoint;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class RouteDaoImpl extends AbstractGenericDao<Route> implements RouteDao {

    private static final Logger LOG = Logger.getLogger(RouteDaoImpl.class.getName());

    private static final String GET_ROUTE_BY_NAME = "SELECT r FROM Route r WHERE r.name = :name";

    private static final String GET_ROUTE_BY_TRAIN_NUMBER = "SELECT route FROM Train t WHERE t.number = :trainNumber";

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

    // TODO экспериментальный метод вытащить Route из Train, проверить работоспособность
    // TODO UPD: проверено, вроде работает
    @Override
    public Route getRouteByTrainNumber(int trainNumber) {
        Query query = getCurrentSession().createQuery(GET_ROUTE_BY_TRAIN_NUMBER);
        query.setParameter("trainNumber", trainNumber);

        Route route = null;
        try {
            route = (Route) query.getSingleResult();
        } catch (Exception e) {
            LOG.warn(e);
        }
        return route;
}

    @Override
    public List<RoutePoint> getRoutePoints(Route route) {
        return route.getRoutePointList();
    }
}