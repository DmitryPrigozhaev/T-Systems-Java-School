package com.railwaycompany.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "stations")
public class Station implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "station_id")
    private Long id;

    @Column(name = "station_name", unique = true, nullable = false)
    private String stationName;

    @Column(name = "station_status", unique = true, nullable = false)
    private Boolean stationStatus;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String station_name) {
        this.stationName = station_name;
    }

    public boolean getStationStatus() {
        return stationStatus;
    }

    public void setStationStatus(Boolean station_status) {
        this.stationStatus = station_status;
    }
}
