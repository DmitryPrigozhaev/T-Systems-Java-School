package com.railwaycompany.services.exceptions;

public class InvalidInputDataException extends RailwayCompanyExceptions {
    public InvalidInputDataException(String message) {
        super(message);
    }

    public InvalidInputDataException(String message, Throwable e) {
        super(message, e);
    }
}
