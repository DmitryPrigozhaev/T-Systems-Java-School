package com.railwaycompany.dao.api;

import com.railwaycompany.entities.RoutePoint;

import java.util.List;

public interface RoutePointDao extends GenericDao<RoutePoint> {

    List<RoutePoint> getStationScheduleByStationId(long stationId);

    List<RoutePoint> getStationScheduleByStationName(String stationName);

    List<RoutePoint> getRouteScheduleByRouteId(long routeId);

}