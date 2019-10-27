package com.torrecampo.app;

public class ParkingSpot {
    Car car;
    public boolean open;

    public ParkingSpot() {
        this.open = true;
    }

    public void parkCar(Car c) {
        if (open) {
            this.car = c;
            this.open = false;
            System.out.println(car.getID() + " has been parked.");
        }
    }

    public void removeCar() {
        if (!open) {
            this.open = true;
            System.out.println(car.getID() + " has left the spot. They were here for " + car.getDuration()
                    + " minutes, and got here at " + car.ticket.timestamp + ". They will be charged $"
                    + car.ticket.getPrice(car.getDuration()));
        }
    }

    public boolean isOpen() {
        return open ? true : false;
    }

}
