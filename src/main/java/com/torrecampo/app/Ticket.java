package com.torrecampo.app;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;

public class Ticket {
    protected Instant timestamp;
    double price;

    public Ticket(double pr) {
        this.timestamp = Instant.now();
        this.price = pr;
    }

    public void printTicketInfo() {
        System.out.println("Timestamp: " + this.timestamp);
    }

    public Instant getTicket() {
        return this.timestamp;
    }

    public double getPrice(int dur) {
        return BigDecimal.valueOf(price * dur).setScale(3, RoundingMode.HALF_UP).doubleValue();
    }
}