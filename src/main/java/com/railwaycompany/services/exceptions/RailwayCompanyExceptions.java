package com.railwaycompany.services.exceptions;

public class RailwayCompanyExceptions extends RuntimeException {
    public RailwayCompanyExceptions(String message) {
        super(message);
    }

    public RailwayCompanyExceptions(String message, Throwable e) {
        super(message, e);
    }
}