package com.railwaycompany.dao.api;

import com.railwaycompany.entities.RoutePoint;

import java.util.List;

/**
 * Interface for Data Access Object of {@link RoutePoint}
 *
 * @author Dmitry Prigozhaev
 * @version 1.0
 */

public interface RoutePointDao extends GenericDao<RoutePoint> {

    List<RoutePoint> getRoutePointsByStationId(long stationId);

    List<RoutePoint> getRoutePointsByStationName(String stationName);

    List<RoutePoint> getRoutePointsByRouteId(long routeId);

    List<RoutePoint> getRoutePointsByRouteName(String name);

}