package com.oocl;

public class NotEnoughPositionException extends RuntimeException {
    public NotEnoughPositionException(String errorMessage) {
        super(errorMessage);
    }
}
