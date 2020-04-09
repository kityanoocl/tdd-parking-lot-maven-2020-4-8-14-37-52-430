package com.oocl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ParkingBoyTest {
    ParkingLot parkingLot;
    ParkingBoy parkingBoy;
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Before
    public void setBefore() {
        parkingLot = new ParkingLot();
        parkingBoy = new ParkingBoy(parkingLot);
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
        exceptionRule.expect(UnrecognizedParkingTicketException.class);
        exceptionRule.expectMessage("Unrecognized parking ticket.");
        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);
        parkingBoy.fetch(parkingTicket);
        Car carThatFetch = parkingBoy.fetch(parkingTicket);
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
        exceptionRule.expect(NullParkingTicketException.class);
        exceptionRule.expectMessage("Please provide your parking ticket.");
        parkingBoy.park(new Car());
        Car car = parkingBoy.fetch(null);
    }

    @Test
    public void should_not_car_ticket_when_same_car_pass() {
        Car car = new Car();
        parkingBoy.park(car);
        Assert.assertNull(parkingBoy.park(car));
    }

    @Test
    public void should_get_unrecognized_parking_ticket_if_query_error_msg() {
        exceptionRule.expect(UnrecognizedParkingTicketException.class);
        exceptionRule.expectMessage("Unrecognized parking ticket.");
        parkingBoy.fetch(new ParkingTicket());
    }

    @Test
    public void should_return_please_provide_your_parking_ticket_if_no_parking_ticket_provided() {
        exceptionRule.expect(NullParkingTicketException.class);
        exceptionRule.expectMessage("Please provide your parking ticket.");
        parkingBoy.fetch(null);
    }

    @Test
    public void should_return_not_enough_position_if_parking_lot_is_full() {
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());
        Assert.assertEquals("Not enough position.", parkingBoy.queryError());
    }

    @Test
    public void should_park_car_in_parking_lot_2_for_a_parking_boy() {
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingLot[] parkingLots = {parkingLot1, parkingLot2};
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        parkingBoy.park(new Car());
        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);
        Car carThatFetch = parkingLot2.fetch(parkingTicket);
        Assert.assertEquals(car, carThatFetch);
    }
}