package com.oocl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParkingBoy {
    public final static String UNRECOGNIZED_PARKING_TICKET = "Unrecognized parking ticket.";
    public final static String PLEASE_PROVIDE_YOUR_PARKING_TICKET = "Please provide your parking ticket.";
    public final static String NOT_ENOUGH_POSITION = "Not enough position.";
    protected List<ParkingLot> parkingLots = new ArrayList<>();
    private ParkingLot selectedParkingLot;

    public ParkingBoy(ParkingLot... parkingLots) {
        this.parkingLots.addAll(Arrays.asList(parkingLots));
        this.selectedParkingLot = this.parkingLots.stream().findFirst().orElse(null);
    }

    public ParkingLot getAvailableParkingLot() {
        return parkingLots.stream().filter(parkingLot -> !parkingLot.isFull()).findFirst().orElse(null);
    }

    public ParkingLot getCarExistParkingLot(ParkingTicket parkingTicket) {
        return parkingLots.stream().filter(parkingLot -> parkingLot.isTicketExist(parkingTicket)).findFirst().orElse(null);
    }

    public boolean isCarExistParkingLot(Car car) {
        return parkingLots.stream().anyMatch(parkingLot -> parkingLot.isCarParked(car));
    }

    public boolean isParkingLotsFull() {
        return parkingLots.stream().allMatch(ParkingLot::isFull);
    }

    public boolean canParkCar(Car car) {
        boolean isCarNull = car == null;
        if (isCarNull) {
            return false;
        }

        if (isCarExistParkingLot(car)) {
            return false;
        }

        return !isParkingLotsFull();
    }

    public ParkingTicket park(Car car) {
        if (isParkingLotsFull()) {
            throw new NotEnoughPositionException(NOT_ENOUGH_POSITION);
        }

        if (!canParkCar(car)) {
            return null;
        }

        ParkingTicket parkingTicket = new ParkingTicket();
        selectedParkingLot = getAvailableParkingLot();
        selectedParkingLot.park(parkingTicket, car);
        return parkingTicket;
    }

    public boolean canFetchCar(ParkingTicket parkingTicket) {
        return parkingLots.stream().anyMatch(parkingLot -> parkingLot.isTicketExist(parkingTicket));
    }

    public Car fetch(ParkingTicket parkingTicket) {
        boolean isParkingTicketNull = parkingTicket == null;
        if (isParkingTicketNull) {
            throw new NullParkingTicketException(PLEASE_PROVIDE_YOUR_PARKING_TICKET);
        }

        if (!canFetchCar(parkingTicket)) {
            throw new UnrecognizedParkingTicketException(UNRECOGNIZED_PARKING_TICKET);
        }

        selectedParkingLot = getCarExistParkingLot(parkingTicket);
        return selectedParkingLot.fetch(parkingTicket);
    }
}
