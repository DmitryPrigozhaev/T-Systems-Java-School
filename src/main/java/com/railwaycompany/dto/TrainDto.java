package com.railwaycompany.dto;

public class TrainDto {

    private Long id;
    private Integer number;
    private String routeName;
    private Integer numberOfCarriages;
    private String stationDeparture;
    private String stationArrival;
    private String datetimeDeparture;
    private String datetimeArrival;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public Integer getNumberOfCarriages() {
        return numberOfCarriages;
    }

    public void setNumberOfCarriages(Integer numberOfCarriages) {
        this.numberOfCarriages = numberOfCarriages;
    }

    public String getStationDeparture() {
        return stationDeparture;
    }

    public void setStationDeparture(String stationDeparture) {
        this.stationDeparture = stationDeparture;
    }

    public String getStationArrival() {
        return stationArrival;
    }

    public void setStationArrival(String stationArrival) {
        this.stationArrival = stationArrival;
    }

    public String getDatetimeDeparture() {
        return datetimeDeparture;
    }

    public void setDatetimeDeparture(String datetimeDeparture) {
        this.datetimeDeparture = datetimeDeparture;
    }

    public String getDatetimeArrival() {
        return datetimeArrival;
    }

    public void setDatetimeArrival(String datetimeArrival) {
        this.datetimeArrival = datetimeArrival;
    }
}
