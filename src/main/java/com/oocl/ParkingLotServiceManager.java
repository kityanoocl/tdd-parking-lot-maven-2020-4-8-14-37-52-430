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
        boolean isParkingBoyNotExist = parkingBoys.stream().filter(parkingBoy-> parkingBoy == selectedParkingBoy).findFirst().orElse(null) == null;
        if (isParkingBoyNotExist) {
            return null;
        }
        return selectedParkingBoy.park(car);
    }
}
