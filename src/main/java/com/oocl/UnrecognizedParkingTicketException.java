package com.oocl;

public class UnrecognizedParkingTicketException extends RuntimeException {
    public UnrecognizedParkingTicketException(String errorMessage) {
        super(errorMessage);
    }
}
