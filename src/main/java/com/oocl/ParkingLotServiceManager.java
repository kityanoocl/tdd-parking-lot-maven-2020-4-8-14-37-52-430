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
        boolean isParkingBoyNotExist = parkingBoys.stream().filter(parkingBoy -> parkingBoy == selectedParkingBoy).findFirst().orElse(null) == null;
        if (isParkingBoyNotExist) {
            return null;
        }
        return selectedParkingBoy.park(car);
    }

    public Car fetch(ParkingBoy selectedParkingBoy, ParkingTicket parkingTicket) {
        boolean isParkingBoyNotExist = parkingBoys.stream().filter(parkingBoy -> parkingBoy == selectedParkingBoy).findFirst().orElse(null) == null;
        if (isParkingBoyNotExist) {
            return null;
        }
        return selectedParkingBoy.fetch(parkingTicket);
    }

    public ParkingTicket askParkingBoyToPark(Car car) {
        boolean isCarNull = car == null;
        if (isCarNull) {
            return null;
        }
        ParkingBoy parkingBoyWithVacancy = parkingBoys.stream().filter(parkingBoy -> {
            try {
                return parkingBoy.canParkCar(car);
            } catch (Exception exception) {
                return false;
            }
        }).findFirst().orElseThrow(() -> new NotEnoughPositionException(NOT_ENOUGH_POSITION));

        return parkingBoyWithVacancy.park(car);
    }

    public Car askParkingBoyToFetch(ParkingTicket parkingTicket) {
        boolean isParkingTicketNull = parkingTicket == null;
        if (parkingTicket == null) {
            throw new NullParkingTicketException(PLEASE_PROVIDE_YOUR_PARKING_TICKET);
        }
        ParkingBoy parkingBoyWithCar = parkingBoys.stream().filter(parkingBoy -> {
            try {
                return parkingBoy.canFetchCar(parkingTicket);
            } catch (Exception exception) {
                return false;
            }
        }).findFirst().orElseThrow(() -> new UnrecognizedParkingTicketException(UNRECOGNIZED_PARKING_TICKET));

        return parkingBoyWithCar.fetch(parkingTicket);
    }

    @Override
    public ParkingTicket park(Car car) {
        try {
            return super.park(car);
        } catch (Exception exceptionThrownByManager) {
            return askParkingBoyToPark(car);
        }
    }

    @Override
    public Car fetch(ParkingTicket parkingTicket) {
        try {
            return super.fetch(parkingTicket);
        } catch (Exception exceptionThrownByManager) {
            return askParkingBoyToFetch(parkingTicket);
        }
    }
}
