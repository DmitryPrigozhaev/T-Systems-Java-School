package com.railwaycompany.dao.api;

import com.railwaycompany.entities.Route;

public interface RouteDao extends GenericDao<Route> {

    Route getRouteByName(String name);

}
