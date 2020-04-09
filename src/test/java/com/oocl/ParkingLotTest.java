package com.oocl;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParkingLotTest {
    @Test
    public void should_return_capacity_of_parking_lot() {
        ParkingLot parkingLot = new ParkingLot();
        int capacity = parkingLot.getCapacity();
        Assert.assertEquals(10, capacity);
    }
    @Test
    public void should_return_user_defined_capacity_of_parking_lot() {
        ParkingLot parkingLot = new ParkingLot(7);
        int capacity = parkingLot.getCapacity();
        Assert.assertEquals(7, capacity);
    }
}