package com.railwaycompany.exceptions;

public class StationWithSuchNameDoesNotExistException extends RailwayCompanyExceptions {
    public StationWithSuchNameDoesNotExistException(String message) {
        super(message);
    }
}
