package com.oocl;

import java.util.Comparator;

public class SmartParkingBoy extends ParkingBoy {
    public SmartParkingBoy(ParkingLot... parkingLots) {
        super(parkingLots);
    }

    @Override
    public ParkingLot getAvailableParkingLot() {
        return parkingLots.stream().filter(parkingLot -> !parkingLot.isFull()).max(Comparator.comparingInt(ParkingLot::getNumberOfEmptySlots)).orElse(null);
    }
}
