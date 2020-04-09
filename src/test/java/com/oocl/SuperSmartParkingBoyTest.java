package com.oocl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class SuperSmartParkingBoyTest {
    ParkingLot parkingLot;
    SuperSmartParkingBoy superSmartParkingBoy;
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
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
        exceptionRule.expect(UnrecognizedParkingTicketException.class);
        exceptionRule.expectMessage("Unrecognized parking ticket.");
        Car car = new Car();
        ParkingTicket parkingTicket = superSmartParkingBoy.park(car);
        superSmartParkingBoy.fetch(parkingTicket);
        Car carThatFetch = superSmartParkingBoy.fetch(parkingTicket);
    }

    @Test
    public void should_not_return_parking_ticket_when_parking_lot_fulled() {
        exceptionRule.expect(NotEnoughPositionException.class);
        exceptionRule.expectMessage("Not enough position.");
        ParkingLot parkingLot = new ParkingLot(1);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLot);
        superSmartParkingBoy.park(new Car());
        ParkingTicket parkingTicket = superSmartParkingBoy.park(new Car());
    }

    @Test
    public void should_not_return_parking_ticket_when_no_car_pass() {
        ParkingTicket parkingTicket = superSmartParkingBoy.park(null);
        Assert.assertNull(parkingTicket);
    }

    @Test
    public void should_not_car_ticket_when_no_parking_ticket_pass() {
        exceptionRule.expect(NullParkingTicketException.class);
        exceptionRule.expectMessage("Please provide your parking ticket.");
        superSmartParkingBoy.park(new Car());
        Car car = superSmartParkingBoy.fetch(null);
    }

    @Test
    public void should_not_car_ticket_when_same_car_pass() {
        Car car = new Car();
        superSmartParkingBoy.park(car);
        Assert.assertNull(superSmartParkingBoy.park(car));
    }

    @Test
    public void should_get_unrecognized_parking_ticket_if_query_error_msg() {
        exceptionRule.expect(UnrecognizedParkingTicketException.class);
        exceptionRule.expectMessage("Unrecognized parking ticket.");
        superSmartParkingBoy.fetch(new ParkingTicket());
    }

    @Test
    public void should_return_please_provide_your_parking_ticket_if_no_parking_ticket_provided() {
        exceptionRule.expect(NullParkingTicketException.class);
        exceptionRule.expectMessage("Please provide your parking ticket.");
        superSmartParkingBoy.fetch(null);
    }

    @Test
    public void should_return_not_enough_position_if_parking_lot_is_full() {
        exceptionRule.expect(NotEnoughPositionException.class);
        exceptionRule.expectMessage("Not enough position.");
        ParkingLot parkingLot = new ParkingLot(1);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLot);
        superSmartParkingBoy.park(new Car());
        superSmartParkingBoy.park(new Car());
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
