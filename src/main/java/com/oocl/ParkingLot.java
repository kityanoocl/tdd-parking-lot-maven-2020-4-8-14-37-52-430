package com.oocl;

public class ParkingLot {
    public static final int DEFAULT_CAPACITY = 10;
    private int capacity;
    public ParkingLot() {
        this.capacity = DEFAULT_CAPACITY;
    }

    public int getCapacity() {
        return this.capacity;
    }
}
