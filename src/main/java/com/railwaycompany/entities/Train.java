package com.railwaycompany.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "trains")
public class Train implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "number", unique = true)
    private Integer number;

    @OneToOne
    @JoinColumn(name = "route_id", unique = true)
    private Route route;

    @Column(name = "number_of_carriages")
    private Integer numberOfCarriages;

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

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route routeId) {
        this.route = routeId;
    }

    public Integer getNumberOfCarriages() {
        return numberOfCarriages;
    }

    public void setNumberOfCarriages(Integer numberOfCarriages) {
        this.numberOfCarriages = numberOfCarriages;
    }
}