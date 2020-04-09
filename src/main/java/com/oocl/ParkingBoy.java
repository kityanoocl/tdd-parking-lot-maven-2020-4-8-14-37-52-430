package com.oocl;

public class ParkingBoy {
    private ParkingLot parkingLot;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(Car car) {
        if (this.parkingLot.isFull()) {
            return null;
        }
        ParkingTicket parkingTicket = new ParkingTicket();
        this.parkingLot.park(parkingTicket, car);
        return parkingTicket;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return this.parkingLot.fetch(parkingTicket);
    }
}
