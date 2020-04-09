package com.oocl;

import java.util.Comparator;

public class ParkingLotEmptySlotsComparator implements Comparator<ParkingLot> {
    @Override
    public int compare(ParkingLot parkingLot1, ParkingLot parkingLot2) {
        return parkingLot1.getNumberOfEmptySlots() - parkingLot2.getNumberOfEmptySlots();
    }
}
