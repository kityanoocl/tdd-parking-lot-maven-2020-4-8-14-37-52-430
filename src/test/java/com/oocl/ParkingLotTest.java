package com.oocl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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

    @Test
    public void should_return_parking_lot_is_full() {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(new ParkingTicket(), new Car());
        Assert.assertTrue(parkingLot.isFull());
    }

    @Test
    public void should_return_true_if_same_car_parked() {
        Car car = new Car();
        parkingLot.park(new ParkingTicket(), car);
        Assert.assertTrue(parkingLot.isCarParked(car));
    }

    @Test
    public void should_return_false_if_no_parking_ticket_found() {
        Car car = new Car();
        parkingLot.park(new ParkingTicket(), car);
        Assert.assertFalse(parkingLot.isTicketExist(new ParkingTicket()));
    }
}