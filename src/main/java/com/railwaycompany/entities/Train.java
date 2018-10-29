package com.railwaycompany.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "trains")
public class Train implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "train_id")
    private Long id;

    // TODO ДИКИЙ ВОПРОС ПРО СВЯЗЬ МАРШРУТ-ПОЕЗД
    @Column(name = "number")
    private Integer number;

    @Column(name = "seats")
    private Integer seats;

    @Column(name = "train_status")
    private String status;

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

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
