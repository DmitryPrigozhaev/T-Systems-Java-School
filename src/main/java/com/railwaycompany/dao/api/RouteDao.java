package com.railwaycompany.dao.api;

import com.railwaycompany.entities.Route;
import com.railwaycompany.entities.RoutePoint;

import java.util.List;

/**
 * Interface for Data Access Object of {@link Route}
 *
 * @author Dmitry Prigozhaev
 * @version 1.0
 */

public interface RouteDao extends GenericDao<Route> {

    Route getRouteByName(String name);

    Route getRouteByTrainNumber(int trainNumber);

    List<RoutePoint> getRoutePoints(Route route);

}