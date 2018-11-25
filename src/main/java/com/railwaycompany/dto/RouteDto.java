package com.railwaycompany.dto;

import java.util.List;

public class RouteDto {

    private Long id;
    private String name;
    private List<RoutePointDto> routePointDtoList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RoutePointDto> getRoutePointDtoList() {
        return routePointDtoList;
    }

    public void setRoutePointDtoList(List<RoutePointDto> routePointDtoList) {
        this.routePointDtoList = routePointDtoList;
    }
}