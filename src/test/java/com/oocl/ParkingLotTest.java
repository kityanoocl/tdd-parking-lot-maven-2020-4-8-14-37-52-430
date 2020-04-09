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

    @Test
    public void should_return_parking_ticket_when_parking_boy_park() {
        ParkingTicket parkingTicket = parkingBoy.park(new Car());
        Assert.assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_car_when_parking_boy_fetch() {
        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);
        Car carThatFetch = parkingBoy.fetch(parkingTicket);
        Assert.assertEquals(car, carThatFetch);
    }

    @Test
    public void should_not_return_car_when_parking_ticket_used() {
        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);
        parkingBoy.fetch(parkingTicket);
        Car carThatFetch = parkingBoy.fetch(parkingTicket);
        Assert.assertNull(carThatFetch);
    }

    @Test
    public void should_not_return_parking_ticket_when_parking_lot_fulled() {
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        parkingBoy.park(new Car());
        ParkingTicket parkingTicket = parkingBoy.park(new Car());
        Assert.assertNull(parkingTicket);
    }

    @Test
    public void should_not_return_parking_ticket_when_no_car_pass() {
        ParkingTicket parkingTicket = parkingBoy.park(null);
        Assert.assertNull(parkingTicket);
    }

    @Test
    public void should_not_car_ticket_when_no_parking_ticket_pass() {
        parkingBoy.park(new Car());
        Car car = parkingBoy.fetch(null);
        Assert.assertNull(car);
    }
}