package com.railwaycompany.dto;

/**
 * Simple Data Transfer Object for {@link com.railwaycompany.entities.RoutePoint}
 *
 * @author Dmitry Prigozhaev
 * @version 1.0
 */

public class RoutePointDto {

    private Long id;
    private Integer trainNumber;
    private String routeName;
    private String stationName;
    private String dateArrival;
    private String dateDeparture;

    public Long getId() {
        return id;
    }

    public Integer getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(Integer trainNumber) {
        this.trainNumber = trainNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
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