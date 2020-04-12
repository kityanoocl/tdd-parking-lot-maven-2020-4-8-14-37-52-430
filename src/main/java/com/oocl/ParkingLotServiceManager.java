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
        ParkingBoy parkingBoyWithCar = parkingBoys.stream().filter(parkingBoy -> {
            try {
                return parkingBoy.canFetchCar(parkingTicket);
            } catch (Exception exception) {
                return false;
            }
        }).findFirst().orElse(null);
        if (parkingBoyWithCar == null) {
            return null;
        }
        return parkingBoyWithCar.fetch(parkingTicket);
    }
}
