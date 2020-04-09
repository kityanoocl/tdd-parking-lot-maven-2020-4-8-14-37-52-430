package com.oocl;

public class ParkingBoy {
    public final static String UNRECOGNIZED_PARKING_TICKET = "Unrecognized parking ticket.";
    public final static String PLEASE_PROVIDE_YOUR_PARKING_TICKET = "Please provide your parking ticket.";
    private ParkingLot parkingLot;
    private String perviousErrorMsg;
    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(Car car) {
        boolean isCarNull = car == null;
        if (isCarNull || this.parkingLot.isFull() || this.parkingLot.isCarParked(car)) {
            return null;
        }
        ParkingTicket parkingTicket = new ParkingTicket();
        this.parkingLot.park(parkingTicket, car);
        return parkingTicket;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if (parkingTicket == null) {
            perviousErrorMsg = PLEASE_PROVIDE_YOUR_PARKING_TICKET;
            return null;
        }

        if (!this.parkingLot.isTicketExist(parkingTicket)) {
            perviousErrorMsg = UNRECOGNIZED_PARKING_TICKET;
            return null;
        }
        return this.parkingLot.fetch(parkingTicket);
    }

    public String queryError() {
        return perviousErrorMsg;
    }
}
