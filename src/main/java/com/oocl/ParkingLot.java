package com.oocl;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    public static final int DEFAULT_CAPACITY = 10;
    private int capacity;
    Map<String, Car> parkingMap = new HashMap<String, Car>();
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
