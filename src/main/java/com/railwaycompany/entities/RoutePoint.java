package com.railwaycompany.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ROUTE_POINT")
public class RoutePoint implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROUTE_ID")
    private int id;

    @ManyToOne
    @JoinColumn(name = "TRAIN_NUMBER")
    private Train train;

    @ManyToOne
    @JoinColumn(name = "STATION_NAME")
    private Station station;

    @Column(name = "DATE_ARRIVAL")
    private Date dateArrival;

    @Column(name = "DATE_DEPARTURE")
    private Date dateDeparture;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
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
