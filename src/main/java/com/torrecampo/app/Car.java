package com.torrecampo.app;

public class Car {
    protected String ID;
    protected Ticket ticket;
    private boolean hasTicket;
    protected int duration;
    private ParkingLot lot;
    double price = 0;

    public Car(String identification, int dur) {
        this.ID = identification;
        this.duration = dur;
    }

    String getID() {
        return this.ID;
    }

    void recieveTicket(Ticket reciever) {
        this.ticket = reciever;
        this.hasTicket = true;
    }

    Ticket returnTicket() {
        this.hasTicket = false;
        return this.ticket;
    }

    boolean hasTicket() {
        return this.hasTicket ? true : false;
    }

    int getDuration() {
        return this.duration;
    }

    boolean atLot() {
        return this.lot == null ? false : true;
    }
}