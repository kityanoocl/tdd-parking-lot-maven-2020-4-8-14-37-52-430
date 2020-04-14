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

    public ParkingLot getCarExistParkingLot(Car car) {
        return parkingLots.stream().filter(parkingLot -> parkingLot.isCarParked(car)).findFirst().orElse(null);
    }

    public boolean canParkCar(Car car) {
        boolean isCarNull = car == null;
        boolean isAlreadyPark = getCarExistParkingLot(car) != null;
        if (isCarNull || isAlreadyPark) {
            return false;
        }
        selectedParkingLot = getAvailableParkingLot();
        return selectedParkingLot != null;
    }

    public ParkingTicket park(Car car) {
        selectedParkingLot = getAvailableParkingLot();
        boolean isFull = selectedParkingLot == null;
        if (isFull) {
            throw new NotEnoughPositionException(NOT_ENOUGH_POSITION);
        }

        if (!canParkCar(car)) {
            return null;
        }

        ParkingTicket parkingTicket = new ParkingTicket();
        selectedParkingLot.park(parkingTicket, car);
        return parkingTicket;
    }

    public boolean canFetchCar(ParkingTicket parkingTicket) {
        selectedParkingLot = getCarExistParkingLot(parkingTicket);
        return selectedParkingLot != null;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if (parkingTicket == null) {
            throw new NullParkingTicketException(PLEASE_PROVIDE_YOUR_PARKING_TICKET);
        }

        selectedParkingLot = getCarExistParkingLot(parkingTicket);
        if (selectedParkingLot == null) {
            throw new UnrecognizedParkingTicketException(UNRECOGNIZED_PARKING_TICKET);
        }

        return selectedParkingLot.fetch(parkingTicket);
    }
}
