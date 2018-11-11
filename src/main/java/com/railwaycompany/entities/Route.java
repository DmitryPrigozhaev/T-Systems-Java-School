package com.railwaycompany.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "routes")
public class Route implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    private List<RoutePoint> routePointList;

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

    public List<RoutePoint> getRoutePointList() {
        return routePointList;
    }

    public void setRoutePointList(List<RoutePoint> routePointList) {
        this.routePointList = routePointList;
    }
}