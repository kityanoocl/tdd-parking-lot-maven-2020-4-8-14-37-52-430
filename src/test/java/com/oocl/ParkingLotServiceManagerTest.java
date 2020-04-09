package com.oocl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotServiceManagerTest {
    ParkingLot parkingLot;
    ParkingLotServiceManager parkingLotServiceManager;

    @Before
    public void setBefore() {
        parkingLot = new ParkingLot();
        parkingLotServiceManager = new ParkingLotServiceManager(parkingLot);
    }

    @Test
    public void should_return_parking_ticket_when_parking_boy_park() {
        ParkingTicket parkingTicket = parkingLotServiceManager.park(new Car());
        Assert.assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_car_when_parking_boy_fetch() {
        Car car = new Car();
        ParkingTicket parkingTicket = parkingLotServiceManager.park(car);
        Car carThatFetch = parkingLotServiceManager.fetch(parkingTicket);
        Assert.assertEquals(car, carThatFetch);
    }

    @Test
    public void should_not_return_car_when_parking_ticket_used() {
        Car car = new Car();
        ParkingTicket parkingTicket = parkingLotServiceManager.park(car);
        parkingLotServiceManager.fetch(parkingTicket);
        Car carThatFetch = parkingLotServiceManager.fetch(parkingTicket);
        Assert.assertNull(carThatFetch);
    }

    @Test
    public void should_not_return_parking_ticket_when_parking_lot_fulled() {
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(parkingLot);
        parkingLotServiceManager.park(new Car());
        ParkingTicket parkingTicket = parkingLotServiceManager.park(new Car());
        Assert.assertNull(parkingTicket);
    }

    @Test
    public void should_not_return_parking_ticket_when_no_car_pass() {
        ParkingTicket parkingTicket = parkingLotServiceManager.park(null);
        Assert.assertNull(parkingTicket);
    }

    @Test
    public void should_not_car_ticket_when_no_parking_ticket_pass() {
        parkingLotServiceManager.park(new Car());
        Car car = parkingLotServiceManager.fetch(null);
        Assert.assertNull(car);
    }

    @Test
    public void should_not_car_ticket_when_same_car_pass() {
        Car car = new Car();
        parkingLotServiceManager.park(car);
        Assert.assertNull(parkingLotServiceManager.park(car));
    }

    @Test
    public void should_get_unrecognized_parking_ticket_if_query_error_msg() {
        parkingLotServiceManager.fetch(new ParkingTicket());
        Assert.assertEquals("Unrecognized parking ticket.", parkingLotServiceManager.queryError());
    }

    @Test
    public void should_return_please_provide_your_parking_ticket_if_no_parking_ticket_provided() {
        parkingLotServiceManager.fetch(null);
        Assert.assertEquals("Please provide your parking ticket.", parkingLotServiceManager.queryError());
    }

    @Test
    public void should_return_not_enough_position_if_parking_lot_is_full() {
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(parkingLot);
        parkingLotServiceManager.park(new Car());
        parkingLotServiceManager.park(new Car());
        Assert.assertEquals("Not enough position.", parkingLotServiceManager.queryError());
    }

    @Test
    public void should_park_car_in_parking_lot_2_for_a_parking_boy() {
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingLot[] parkingLots = {parkingLot1, parkingLot2};
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(parkingLots);
        parkingLotServiceManager.park(new Car());
        Car car = new Car();
        ParkingTicket parkingTicket = parkingLotServiceManager.park(car);
        Car carThatFetch = parkingLot2.fetch(parkingTicket);
        Assert.assertEquals(car, carThatFetch);
    }
}