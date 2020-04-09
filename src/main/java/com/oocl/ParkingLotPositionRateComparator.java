package com.oocl;

import java.util.Comparator;

public class ParkingLotPositionRateComparator implements Comparator<ParkingLot> {
    @Override
    public int compare(ParkingLot parkingLot1, ParkingLot parkingLot2) {
        double result = parkingLot1.getPositionRate() - parkingLot2.getPositionRate();
        if (result > 0) {
            return 1;
        } else if (result < 0) {
            return -1;
        } else {
            return 0;
        }
    }
}
