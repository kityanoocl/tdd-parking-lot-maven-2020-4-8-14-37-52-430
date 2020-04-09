package com.oocl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParkingLotServiceManagerTest {
    ParkingLot parkingLot;
    ParkingLotServiceManager parkingLotServiceManager;
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
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
        exceptionRule.expect(UnrecognizedParkingTicketException.class);
        exceptionRule.expectMessage("Unrecognized parking ticket.");
        Car car = new Car();
        ParkingTicket parkingTicket = parkingLotServiceManager.park(car);
        parkingLotServiceManager.fetch(parkingTicket);
        Car carThatFetch = parkingLotServiceManager.fetch(parkingTicket);
    }

    @Test
    public void should_not_return_parking_ticket_when_parking_lot_fulled() {
        exceptionRule.expect(NotEnoughPositionException.class);
        exceptionRule.expectMessage("Not enough position.");
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(parkingLot);
        parkingLotServiceManager.park(new Car());
        ParkingTicket parkingTicket = parkingLotServiceManager.park(new Car());
    }

    @Test
    public void should_not_return_parking_ticket_when_no_car_pass() {
        ParkingTicket parkingTicket = parkingLotServiceManager.park(null);
        Assert.assertNull(parkingTicket);
    }

    @Test
    public void should_not_car_ticket_when_no_parking_ticket_pass() {
        exceptionRule.expect(NullParkingTicketException.class);
        exceptionRule.expectMessage("Please provide your parking ticket.");
        parkingLotServiceManager.park(new Car());
        Car car = parkingLotServiceManager.fetch(null);
    }

    @Test
    public void should_not_car_ticket_when_same_car_pass() {
        Car car = new Car();
        parkingLotServiceManager.park(car);
        Assert.assertNull(parkingLotServiceManager.park(car));
    }

    @Test
    public void should_get_unrecognized_parking_ticket_if_query_error_msg() {
        exceptionRule.expect(UnrecognizedParkingTicketException.class);
        exceptionRule.expectMessage("Unrecognized parking ticket.");
        parkingLotServiceManager.fetch(new ParkingTicket());
    }

    @Test
    public void should_return_please_provide_your_parking_ticket_if_no_parking_ticket_provided() {
        exceptionRule.expect(NullParkingTicketException.class);
        exceptionRule.expectMessage("Please provide your parking ticket.");
        parkingLotServiceManager.fetch(null);
    }

    @Test
    public void should_return_not_enough_position_if_parking_lot_is_full() {
        exceptionRule.expect(NotEnoughPositionException.class);
        exceptionRule.expectMessage("Not enough position.");
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(parkingLot);
        parkingLotServiceManager.park(new Car());
        parkingLotServiceManager.park(new Car());
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

    @Test
    public void should_add_3_types_of_parking_boys_in_management_list() {
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLot);
        ParkingBoy[] parkingBoys = {parkingBoy, smartParkingBoy, superSmartParkingBoy};
        parkingLotServiceManager.assignParkingBoyToManagement(parkingBoys);
        Assert.assertEquals(Arrays.asList(parkingBoys),
                parkingLotServiceManager.getManagementList());
    }

    @Test
    public void should_specify_parking_boy_to_park_a_car() {
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(new ParkingLot());
        ParkingLot parkingLot = new ParkingLot(3);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot);
        ParkingBoy[] parkingBoys = {parkingBoy, smartParkingBoy, superSmartParkingBoy};
        parkingLotServiceManager.assignParkingBoyToManagement(parkingBoys);
        Car car = new Car();
        ParkingTicket parkingTicket = parkingLotServiceManager.park(smartParkingBoy, car);
        Car carThatFetch = smartParkingBoy.fetch(parkingTicket);
        Assert.assertEquals(car, carThatFetch);
    }

    @Test
    public void should_specify_parking_boy_to_fetch_a_car() {
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(new ParkingLot());
        ParkingLot parkingLot = new ParkingLot(3);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot);
        ParkingBoy[] parkingBoys = {parkingBoy, smartParkingBoy, superSmartParkingBoy};
        parkingLotServiceManager.assignParkingBoyToManagement(parkingBoys);
        Car car = new Car();
        ParkingTicket parkingTicket = smartParkingBoy.park(car);
        Car carThatFetch = parkingLotServiceManager.fetch(smartParkingBoy, parkingTicket);
        Assert.assertEquals(car, carThatFetch);
    }
}