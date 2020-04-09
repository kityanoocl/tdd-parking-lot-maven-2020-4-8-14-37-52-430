package com.oocl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParkingLotTest {
    ParkingLot parkingLot;
    ParkingBoy parkingBoy;

    @Before
    public void setBefore() {
        parkingLot = new ParkingLot();
        parkingBoy = new ParkingBoy(parkingLot);
    }

    @Test
    public void should_return_capacity_of_parking_lot() {
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