package com.railwaycompany.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "STATION")
public class Station implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STATION_ID")
    private Long station_id;

    @Column(name = "STATION_NAME", unique = true)
    private String station_name;

    @Column(name = "STATION_STATUS", unique = true)
    private Boolean station_status;


    public Long getId() {
        return station_id;
    }

    public void setId(Long station_id) {
        this.station_id = station_id;
    }

    public String getStation_name() {
        return station_name;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    public boolean getStation_status() {
        return station_status;
    }

    public void setStation_status(boolean station_status) {
        this.station_status = station_status;
    }
}
