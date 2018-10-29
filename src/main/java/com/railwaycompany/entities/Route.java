package com.railwaycompany.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "routes")
public class Route implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "route_id")
    private Long id;

    @Column(name = "route_number")
    private Integer routeNumber;

    @ManyToOne
    @JoinColumn(name = "station_id", nullable = false)
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

    public Integer getRouteNumber() {
        return routeNumber;
    }

    public void setRouteNumber(Integer routeNumber) {
        this.routeNumber = routeNumber;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
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
