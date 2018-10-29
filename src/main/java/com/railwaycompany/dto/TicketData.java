package com.railwaycompany.dto;

import java.util.Date;

public class TicketData {

    private long id;
    private int trainNumber;
    private int carriageNumber;
    private int seatNumber;
    private String stationFrom;
    private String stationTo;
    private Date saleTime;
    private Date dateArrival;
    private Date dateDeparture;
    private UserData userData;
    private TrainData trainData;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    public int getCarriageNumber() {
        return carriageNumber;
    }

    public void setCarriageNumber(int carriageNumber) {
        this.carriageNumber = carriageNumber;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getStationFrom() {
        return stationFrom;
    }

    public void setStationFrom(String stationFrom) {
        this.stationFrom = stationFrom;
    }

    public String getStationTo() {
        return stationTo;
    }

    public void setStationTo(String stationTo) {
        this.stationTo = stationTo;
    }

    public Date getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(Date saleTime) {
        this.saleTime = saleTime;
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

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public TrainData getTrainData() {
        return trainData;
    }

    public void setTrainData(TrainData trainData) {
        this.trainData = trainData;
    }
}
