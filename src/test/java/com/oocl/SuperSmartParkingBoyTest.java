package com.oocl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SuperSmartParkingBoyTest {
    ParkingLot parkingLot;
    SuperSmartParkingBoy superSmartParkingBoy;

    @Before
    public void setBefore() {
        parkingLot = new ParkingLot();
        superSmartParkingBoy = new SuperSmartParkingBoy(parkingLot);
    }

    @Test
    public void should_return_parking_ticket_when_parking_boy_park() {
        ParkingTicket parkingTicket = superSmartParkingBoy.park(new Car());
        Assert.assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_car_when_parking_boy_fetch() {
        Car car = new Car();
        ParkingTicket parkingTicket = superSmartParkingBoy.park(car);
        Car carThatFetch = superSmartParkingBoy.fetch(parkingTicket);
        Assert.assertEquals(car, carThatFetch);
    }

    @Test
    public void should_not_return_car_when_parking_ticket_used() {
        Car car = new Car();
        ParkingTicket parkingTicket = superSmartParkingBoy.park(car);
        superSmartParkingBoy.fetch(parkingTicket);
        Car carThatFetch = superSmartParkingBoy.fetch(parkingTicket);
        Assert.assertNull(carThatFetch);
    }

    @Test
    public void should_not_return_parking_ticket_when_parking_lot_fulled() {
        ParkingLot parkingLot = new ParkingLot(1);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLot);
        superSmartParkingBoy.park(new Car());
        ParkingTicket parkingTicket = superSmartParkingBoy.park(new Car());
        Assert.assertNull(parkingTicket);
    }

    @Test
    public void should_not_return_parking_ticket_when_no_car_pass() {
        ParkingTicket parkingTicket = superSmartParkingBoy.park(null);
        Assert.assertNull(parkingTicket);
    }

    @Test
    public void should_not_car_ticket_when_no_parking_ticket_pass() {
        superSmartParkingBoy.park(new Car());
        Car car = superSmartParkingBoy.fetch(null);
        Assert.assertNull(car);
    }

    @Test
    public void should_not_car_ticket_when_same_car_pass() {
        Car car = new Car();
        superSmartParkingBoy.park(car);
        Assert.assertNull(superSmartParkingBoy.park(car));
    }

    @Test
    public void should_get_unrecognized_parking_ticket_if_query_error_msg() {
        superSmartParkingBoy.fetch(new ParkingTicket());
        Assert.assertEquals("Unrecognized parking ticket.", superSmartParkingBoy.queryError());
    }

    @Test
    public void should_return_please_provide_your_parking_ticket_if_no_parking_ticket_provided() {
        superSmartParkingBoy.fetch(null);
        Assert.assertEquals("Please provide your parking ticket.", superSmartParkingBoy.queryError());
    }

    @Test
    public void should_return_not_enough_position_if_parking_lot_is_full() {
        ParkingLot parkingLot = new ParkingLot(1);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLot);
        superSmartParkingBoy.park(new Car());
        superSmartParkingBoy.park(new Car());
        Assert.assertEquals("Not enough position.", superSmartParkingBoy.queryError());
    }

    @Test
    public void should_park_car_in_parking_lot_2_for_a_parking_boy() {
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingLot[] parkingLots = {parkingLot1, parkingLot2};
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        superSmartParkingBoy.park(new Car());
        Car car = new Car();
        ParkingTicket parkingTicket = superSmartParkingBoy.park(car);
        Car carThatFetch = parkingLot2.fetch(parkingTicket);
        Assert.assertEquals(car, carThatFetch);
    }

    @Test
    public void should_park_car_in_lower_position_rate_parking_lot() {
        ParkingLot parkingLot1 = new ParkingLot(2);
        ParkingLot parkingLot2 = new ParkingLot(100);
        ParkingLot[] parkingLots = {parkingLot1, parkingLot2};
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        superSmartParkingBoy.park(new Car());
        Car car = new Car();
        ParkingTicket parkingTicket = superSmartParkingBoy.park(car);
        Car carThatFetch = parkingLot2.fetch(parkingTicket);
        Assert.assertEquals(car, carThatFetch);
    }
}
