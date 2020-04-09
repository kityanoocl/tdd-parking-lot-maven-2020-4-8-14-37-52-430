package com.oocl;

public class ParkingLot {
    public static final int DEFAULT_CAPACITY = 10;
    private int capacity;
    public ParkingLot() {
        this.capacity = DEFAULT_CAPACITY;
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return this.capacity;
    }
}
