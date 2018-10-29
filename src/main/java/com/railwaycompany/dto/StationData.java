package com.railwaycompany.dto;

public class StationData {

    private long id;
    private String stationName;
    private boolean status;
    // TODO надо добавить расписание?

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
