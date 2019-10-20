package com.torrecampo.app;

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
            System.out.println(car.ID + " has been parked.");
        }
    }

    public void removeCar() {
        if (!open) {
            open = true;
            System.out.println(car.ID + " has left the spot.");
        }
    }

    public boolean isOpen() {
        return open ? true : false;
    }
}