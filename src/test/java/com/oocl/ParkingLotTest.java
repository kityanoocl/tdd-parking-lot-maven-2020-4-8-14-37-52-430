package com.oocl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParkingLotTest {
    ParkingLot parkingLot;
    @Before
    public void setBefore() {
        parkingLot = new ParkingLot();
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
    public void should_return_parking_ticket_when_parking_boy_park() {
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ParkingTicket parkingTicket = parkingBoy.park(new Car());
        Assert.assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_car_when_parking_boy_fetch() {
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);
        Car carThatFetch = parkingBoy.fetch(parkingTicket);
        Assert.assertEquals(car, carThatFetch);
    }
}