package com.oocl;

import java.util.Comparator;

public class SuperSmartParkingBoy extends ParkingBoy {
    public SuperSmartParkingBoy(ParkingLot... parkingLots) {
        super(parkingLots);
    }

    @Override
    public ParkingLot getAvailableParkingLot() {
        return parkingLots.stream().filter(parkingLot -> !parkingLot.isFull()).max(Comparator.comparingDouble(ParkingLot::getPositionRate)).orElse(null);
    }
}
