package com.railwaycompany.dto;

public class RouteDto {

    private String routeName;
    private long station;
    private String dateArrival;
    private String dateDeparture;

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public long getStation() {
        return station;
    }

    public void setStation(long station) {
        this.station = station;
    }

    public String getDateArrival() {
        return dateArrival;
    }

    public void setDateArrival(String dateArrival) {
        this.dateArrival = dateArrival;
    }

    public String getDateDeparture() {
        return dateDeparture;
    }

    public void setDateDeparture(String dateDeparture) {
        this.dateDeparture = dateDeparture;
    }
}