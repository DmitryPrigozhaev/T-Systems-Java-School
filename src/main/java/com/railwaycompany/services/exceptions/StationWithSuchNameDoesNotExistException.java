package com.railwaycompany.services.exceptions;

public class StationWithSuchNameDoesNotExistException extends RailwayCompanyExceptions {
    public StationWithSuchNameDoesNotExistException(String message) {
        super(message);
    }
}
