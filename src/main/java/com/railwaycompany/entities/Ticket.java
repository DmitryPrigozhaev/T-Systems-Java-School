package com.railwaycompany.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "TICKETS")
public class Ticket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TICKET_ID")
    private int id;

    // TODO правильное отношение?
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "SALE_TIME")
    private Date saleTime;

    @ManyToOne
    @JoinColumn(name = "TRAIN_NUMBER")
    private Train train;

    @Column(name = "CARRIAGE")
    private int carriage;

    @Column(name = "PLACE")
    private int place;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(Date saleTime) {
        this.saleTime = saleTime;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public int getCarriage() {
        return carriage;
    }

    public void setCarriage(int carriage) {
        this.carriage = carriage;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }
}
