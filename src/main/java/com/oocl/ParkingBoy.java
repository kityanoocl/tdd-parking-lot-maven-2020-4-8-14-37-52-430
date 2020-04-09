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
    private String previousErrorMsg;

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
        selectedParkingLot = getAvailableParkingLot();
        boolean isCarNull = car == null;
        boolean isAlreadyPark = getCarExistParkingLot(car) != null;
        boolean isFull = selectedParkingLot == null;
        boolean canParkCar = true;
        if (isCarNull || isAlreadyPark) {
            canParkCar = false;
        }else if (isFull) {
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
        selectedParkingLot.park(parkingTicket, car);
        return parkingTicket;
    }

    public boolean canFetchCar(ParkingTicket parkingTicket) {
        selectedParkingLot = getCarExistParkingLot(parkingTicket);
        boolean canFetchCar = true;
        if (parkingTicket == null) {
            previousErrorMsg = PLEASE_PROVIDE_YOUR_PARKING_TICKET;
            canFetchCar = false;
        } else if (selectedParkingLot == null) {
            previousErrorMsg = UNRECOGNIZED_PARKING_TICKET;
            canFetchCar = false;
        }

        return canFetchCar;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if (!canFetchCar(parkingTicket)) {
            return null;
        }
        return selectedParkingLot.fetch(parkingTicket);
    }

    public String queryError() {
        return previousErrorMsg;
    }
}
