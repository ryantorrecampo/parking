package com.torrecampo.app;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ParkingSpot {
    Car car;
    private boolean open;

    public ParkingSpot() {
        this.open = true;
    }

    public void parkCar(Car c) {
        if (open) {
            this.car = c;
            open = false;
            System.out.println(car.getID() + " has been parked.");
        }
    }

    public void removeCar() {
        if (!open) {
            open = true;
            System.out.println(car.getID() + " has left the spot. They were here for " + car.getDuration()
                    + " minutes, and got here at " + car.ticket.timestamp + ". They will be charged $"
                    + BigDecimal.valueOf(car.ticket.getPrice(car.getDuration())).setScale(4, RoundingMode.HALF_UP)
                            .doubleValue());
        }
    }

    public boolean isOpen() {
        return open ? true : false;
    }
}
