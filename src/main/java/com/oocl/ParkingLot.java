package com.oocl;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    public static final int DEFAULT_CAPACITY = 10;
    private int capacity;
    private Map<ParkingTicket, Car> parkingMap = new HashMap<>();

    public ParkingLot() {
        this.capacity = DEFAULT_CAPACITY;
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int getNumberOfEmptySlots() {
        return this.capacity - parkingMap.size();
    }

    public boolean isFull() {
        return parkingMap.size() == capacity;
    }

    public boolean isTicketExist(ParkingTicket parkingTicket) {
        return parkingMap.containsKey(parkingTicket);
    }

    public void park(ParkingTicket parkingTicket, Car car) {
        parkingMap.put(parkingTicket, car);
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return parkingMap.remove(parkingTicket);
    }

    public boolean isCarParked(Car car) {
        return parkingMap.containsValue(car);
    }

    public double getPositionRate() {
        return (this.capacity - parkingMap.size()) / (this.capacity * 1.0);
    }
}
