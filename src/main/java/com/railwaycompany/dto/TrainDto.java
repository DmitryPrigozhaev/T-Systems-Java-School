package com.railwaycompany.dto;

import com.railwaycompany.entities.Route;

public class TrainDto {

    private Long id;
    private Integer number;
    private Route route;
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

    public void setRoute(Route route) {
        this.route = route;
    }

    public Integer getNumberOfCarriages() {
        return numberOfCarriages;
    }

    public void setNumberOfCarriages(Integer numberOfCarriages) {
        this.numberOfCarriages = numberOfCarriages;
    }
}
