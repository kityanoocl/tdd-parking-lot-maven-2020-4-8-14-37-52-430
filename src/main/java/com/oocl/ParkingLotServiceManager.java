package com.oocl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParkingLotServiceManager extends ParkingBoy {
    private List<ParkingBoy> parkingBoys = new ArrayList<>();

    public ParkingLotServiceManager(ParkingLot... parkingLots) {
        super(parkingLots);
    }

    public void assignParkingBoyToManagement(ParkingBoy... parkingBoys) {
        this.parkingBoys.addAll(Arrays.asList(parkingBoys));
    }

    public List<ParkingBoy> getManagementList() {
        return this.parkingBoys;
    }

    public ParkingTicket park(ParkingBoy selectedParkingBoy, Car car) {
        boolean isSelectedParkingBoyNotInParkingBoys = !parkingBoys.contains(selectedParkingBoy);
        if (isSelectedParkingBoyNotInParkingBoys) {
            return null;
        }
        return selectedParkingBoy.park(car);
    }

    public Car fetch(ParkingBoy selectedParkingBoy, ParkingTicket parkingTicket) {
        boolean isSelectedParkingBoyNotInParkingBoys = !parkingBoys.contains(selectedParkingBoy);
        if (isSelectedParkingBoyNotInParkingBoys) {
            return null;
        }
        return selectedParkingBoy.fetch(parkingTicket);
    }

    public ParkingTicket askParkingBoyToPark(Car car) {
        boolean isCarNull = car == null;
        if (isCarNull) {
            return null;
        }
        ParkingBoy parkingBoyWithVacancy = parkingBoys.stream().filter(parkingBoy -> parkingBoy.canParkCar(car)).findFirst().orElseThrow(() -> new NotEnoughPositionException(NOT_ENOUGH_POSITION));

        return parkingBoyWithVacancy.park(car);
    }

    public Car askParkingBoyToFetch(ParkingTicket parkingTicket) {
        boolean isParkingTicketNull = parkingTicket == null;
        if (isParkingTicketNull) {
            throw new NullParkingTicketException(PLEASE_PROVIDE_YOUR_PARKING_TICKET);
        }
        ParkingBoy parkingBoyWithCar = parkingBoys.stream().filter(parkingBoy -> parkingBoy.canFetchCar(parkingTicket)).findFirst().orElseThrow(() -> new UnrecognizedParkingTicketException(UNRECOGNIZED_PARKING_TICKET));

        return parkingBoyWithCar.fetch(parkingTicket);
    }

    @Override
    public ParkingTicket park(Car car) {
        try {
            return askParkingBoyToPark(car);
        } catch (Exception exceptionThrownByParkingBoys) {
            return super.park(car);
        }
    }

    @Override
    public Car fetch(ParkingTicket parkingTicket) {
        try {
            return askParkingBoyToFetch(parkingTicket);
        } catch (Exception exceptionThrownByParkingBoys) {
            return super.fetch(parkingTicket);
        }
    }
}
