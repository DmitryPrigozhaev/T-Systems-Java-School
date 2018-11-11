package com.railwaycompany.dao.api;

import com.railwaycompany.entities.Route;
import com.railwaycompany.entities.RoutePoint;

import java.util.List;

public interface RouteDao extends GenericDao<Route> {

    Route getRouteByName(String name);

    List<RoutePoint> getRoutePoints(Route route);

}
