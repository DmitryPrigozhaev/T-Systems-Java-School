package com.railwaycompany.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "route_points")
public class RoutePoint implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "route_id", referencedColumnName = "id")
    private Route route;

    @ManyToOne
    @JoinColumn(name = "station_id", referencedColumnName = "id", nullable = false)
    private Station station;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "date_arrival")
    private Date dateArrival;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "date_departure")
    private Date dateDeparture;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Route getRoutePointId() {
        return route;
    }

    public void setRoutePointId(Route routePointId) {
        this.route = routePointId;
    }

    public Station getStationId() {
        return station;
    }

    public void setStationId(Station stationId) {
        this.station = stationId;
    }

    public Date getDateArrival() {
        return dateArrival;
    }

    public void setDateArrival(Date dateArrival) {
        this.dateArrival = dateArrival;
    }

    public Date getDateDeparture() {
        return dateDeparture;
    }

    public void setDateDeparture(Date dateDeparture) {
        this.dateDeparture = dateDeparture;
    }
}