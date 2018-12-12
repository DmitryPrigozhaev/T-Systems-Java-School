package com.railwaycompany.dto;

/**
 * @author Dmitry Prigozhaev
 * @version 1.0
 */

public class ScheduleDto {

    private String stationFromName;
    private String stationToName;
    private String date;

    public String getStationFromName() {
        return stationFromName;
    }

    public void setStationFromName(String stationFromName) {
        this.stationFromName = stationFromName;
    }

    public String getStationToName() {
        return stationToName;
    }

    public void setStationToName(String stationToName) {
        this.stationToName = stationToName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
