package com.oocl;

public class SmartParkingBoy extends ParkingBoy {
    public SmartParkingBoy(ParkingLot... parkingLots) {
        super(parkingLots);
    }

    @Override
    public ParkingLot getAvailableParkingLot() {
        return parkingLots.stream().filter(parkingLot -> !parkingLot.isFull()).max(new ParkingLotEmptySlotsComparator()).orElse(null);
    }
}
