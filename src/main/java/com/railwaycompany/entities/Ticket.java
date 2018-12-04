package com.railwaycompany.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "tickets")
public class Ticket implements Serializable, Comparable<Ticket> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "train_id", nullable = false)
    private Train train;

    @ManyToOne
    @JoinColumn(name = "route", nullable = false)
    private Route route;

    @ManyToOne
    @JoinColumn(name = "from_station", nullable = false)
    private Station stationFrom;

    @ManyToOne
    @JoinColumn(name = "to_station", nullable = false)
    private Station stationTo;

    @Temporal(value = TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "sale_time", nullable = false)
    private Date saleTime;

    @Column(name = "carriage", nullable = false)
    private Integer carriage;

    @Column(name = "seat", nullable = false)
    private Integer seat;

    @Column(name = "price", nullable = false)
    private Float price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Station getStationFrom() {
        return stationFrom;
    }

    public void setStationFrom(Station stationFrom) {
        this.stationFrom = stationFrom;
    }

    public Station getStationTo() {
        return stationTo;
    }

    public void setStationTo(Station stationTo) {
        this.stationTo = stationTo;
    }

    public Date getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(Date saleTime) {
        this.saleTime = saleTime;
    }

    public Integer getCarriage() {
        return carriage;
    }

    public void setCarriage(Integer carriage) {
        this.carriage = carriage;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public int compareTo(Ticket ticket) {
        return (int) (this.id - ticket.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id) &&
                Objects.equals(user, ticket.user) &&
                Objects.equals(train, ticket.train) &&
                Objects.equals(route, ticket.route) &&
                Objects.equals(saleTime, ticket.saleTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, saleTime);
    }
}