package com.oocl;

public class ParkingBoy {
    public final static String UNRECOGNIZED_PARKING_TICKET = "Unrecognized parking ticket.";
    public final static String PLEASE_PROVIDE_YOUR_PARKING_TICKET = "Please provide your parking ticket.";
    public final static String NOT_ENOUGH_POSITION = "Not enough position.";
    private ParkingLot parkingLot;
    private String previousErrorMsg;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public boolean canParkCar(Car car) {
        boolean isCarNull = car == null;
        boolean canParkCar = true;
        if (isCarNull || this.parkingLot.isCarParked(car)) {
            canParkCar = false;
        }
        if (this.parkingLot.isFull()) {
            previousErrorMsg = NOT_ENOUGH_POSITION;
            canParkCar = false;
        }
        return canParkCar;
    }

    public ParkingTicket park(Car car) {
        if (!canParkCar(car)) {
            return null;
        }
        ParkingTicket parkingTicket = new ParkingTicket();
        this.parkingLot.park(parkingTicket, car);
        return parkingTicket;
    }

    public boolean canRetrieveCar(ParkingTicket parkingTicket) {
        boolean isAbleToRetrieveCar = true;
        if (parkingTicket == null) {
            previousErrorMsg = PLEASE_PROVIDE_YOUR_PARKING_TICKET;
            isAbleToRetrieveCar = false;
        } else if (!this.parkingLot.isTicketExist(parkingTicket)) {
            previousErrorMsg = UNRECOGNIZED_PARKING_TICKET;
            isAbleToRetrieveCar = false;
        }

        return isAbleToRetrieveCar;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if (!canRetrieveCar(parkingTicket)) {
            return null;
        }
        return this.parkingLot.fetch(parkingTicket);
    }

    public String queryError() {
        return previousErrorMsg;
    }
}
