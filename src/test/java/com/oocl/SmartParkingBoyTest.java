package com.oocl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class SmartParkingBoyTest {
    ParkingLot parkingLot;
    SmartParkingBoy smartParkingBoy;
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    @Before
    public void setBefore() {
        parkingLot = new ParkingLot();
        smartParkingBoy = new SmartParkingBoy(parkingLot);
    }

    @Test
    public void should_return_parking_ticket_when_parking_boy_park() {
        ParkingTicket parkingTicket = smartParkingBoy.park(new Car());
        Assert.assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_car_when_parking_boy_fetch() {
        Car car = new Car();
        ParkingTicket parkingTicket = smartParkingBoy.park(car);
        Car carThatFetch = smartParkingBoy.fetch(parkingTicket);
        Assert.assertEquals(car, carThatFetch);
    }

    @Test
    public void should_not_return_car_when_parking_ticket_used() {
        exceptionRule.expect(UnrecognizedParkingTicketException.class);
        exceptionRule.expectMessage("Unrecognized parking ticket.");
        Car car = new Car();
        ParkingTicket parkingTicket = smartParkingBoy.park(car);
        smartParkingBoy.fetch(parkingTicket);
        Car carThatFetch = smartParkingBoy.fetch(parkingTicket);
    }

    @Test
    public void should_not_return_parking_ticket_when_parking_lot_fulled() {
        ParkingLot parkingLot = new ParkingLot(1);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot);
        smartParkingBoy.park(new Car());
        ParkingTicket parkingTicket = smartParkingBoy.park(new Car());
        Assert.assertNull(parkingTicket);
    }

    @Test
    public void should_not_return_parking_ticket_when_no_car_pass() {
        ParkingTicket parkingTicket = smartParkingBoy.park(null);
        Assert.assertNull(parkingTicket);
    }

    @Test
    public void should_not_car_ticket_when_no_parking_ticket_pass() {
        smartParkingBoy.park(new Car());
        Car car = smartParkingBoy.fetch(null);
        Assert.assertNull(car);
    }

    @Test
    public void should_not_car_ticket_when_same_car_pass() {
        Car car = new Car();
        smartParkingBoy.park(car);
        Assert.assertNull(smartParkingBoy.park(car));
    }

    @Test
    public void should_get_unrecognized_parking_ticket_if_query_error_msg() {
        exceptionRule.expect(UnrecognizedParkingTicketException.class);
        exceptionRule.expectMessage("Unrecognized parking ticket.");
        smartParkingBoy.fetch(new ParkingTicket());
    }

    @Test
    public void should_return_please_provide_your_parking_ticket_if_no_parking_ticket_provided() {
        smartParkingBoy.fetch(null);
        Assert.assertEquals("Please provide your parking ticket.", smartParkingBoy.queryError());
    }

    @Test
    public void should_return_not_enough_position_if_parking_lot_is_full() {
        ParkingLot parkingLot = new ParkingLot(1);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot);
        smartParkingBoy.park(new Car());
        smartParkingBoy.park(new Car());
        Assert.assertEquals("Not enough position.", smartParkingBoy.queryError());
    }

    @Test
    public void should_park_car_in_parking_lot_2_for_a_parking_boy() {
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingLot[] parkingLots = {parkingLot1, parkingLot2};
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        smartParkingBoy.park(new Car());
        Car car = new Car();
        ParkingTicket parkingTicket = smartParkingBoy.park(car);
        Car carThatFetch = parkingLot2.fetch(parkingTicket);
        Assert.assertEquals(car, carThatFetch);
    }

    @Test
    public void should_park_car_in_more_empty_slot_parking_lot() {
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot(100);
        ParkingLot[] parkingLots = {parkingLot1, parkingLot2};
        Car car = new Car();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        ParkingTicket parkingTicket = smartParkingBoy.park(car);
        Car carThatFetch = parkingLot2.fetch(parkingTicket);
        Assert.assertEquals(car, carThatFetch);
    }
}