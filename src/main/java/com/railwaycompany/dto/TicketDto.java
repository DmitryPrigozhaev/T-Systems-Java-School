package com.railwaycompany.dto;

public class TicketDto {

    private String userEmail;
    private Integer trainNumber;
    private Integer carriageNumber;
    private Integer seatNumber;
    private String routeName;
    private String stationFromName;
    private String stationToName;
    private String datetimeDeparture;
    private String datetimeArrival;
    private String saleTime;
    private Float price;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Integer getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(Integer trainNumber) {
        this.trainNumber = trainNumber;
    }

    public Integer getCarriageNumber() {
        return carriageNumber;
    }

    public void setCarriageNumber(Integer carriageNumber) {
        this.carriageNumber = carriageNumber;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

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

    public String getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(String saleTime) {
        this.saleTime = saleTime;
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}