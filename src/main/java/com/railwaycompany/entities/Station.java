package com.railwaycompany.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "STATION")
public class Station implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STATION_ID")
    private int stationId;

    @Column(name = "STATION_NAME", unique = true)
    private String stationName;

    @Column(name = "STATION_STATUS", unique = true)
    private Boolean stationStatus;


    public int getId() {
        return stationId;
    }

    public void setId(int station_id) {
        this.stationId = station_id;
    }

    public String getStation_name() {
        return stationName;
    }

    public void setStation_name(String station_name) {
        this.stationName = station_name;
    }

    public boolean getStation_status() {
        return stationStatus;
    }

    public void setStation_status(boolean station_status) {
        this.stationStatus = station_status;
    }
}
