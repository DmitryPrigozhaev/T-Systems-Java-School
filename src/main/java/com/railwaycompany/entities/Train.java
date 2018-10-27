package com.railwaycompany.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TRAINS")
public class Train implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TRAIN_ID")
    private int id;

    @Column(name = "TRAIN_NUMBER")
    private int number;

    @Column(name = "SEATS")
    private int seats;

    @Column(name = "TRAIN_STATUS")
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
