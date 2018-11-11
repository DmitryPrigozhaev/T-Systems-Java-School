package com.railwaycompany.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "stations")
public class Station implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "station", cascade = CascadeType.ALL)
    private
    Set<RoutePoint> routePointSet;

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

    public Set<RoutePoint> getRoutePointSet() {
        return routePointSet;
    }

    public void setRoutePointSet(Set<RoutePoint> routePointSet) {
        this.routePointSet = routePointSet;
    }
}
