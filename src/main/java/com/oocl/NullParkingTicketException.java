package com.oocl;

public class NullParkingTicketException extends RuntimeException {
    public NullParkingTicketException(String errorMessage) {
        super(errorMessage);
    }
}
