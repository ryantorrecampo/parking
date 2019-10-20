package com.torrecampo.app;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;

public class Ticket {
    protected Instant timestamp;
    // double price;

    public Ticket() {
        this.timestamp = Instant.now();
    }

    public void printTicketInfo() {
        System.out.println("Timestamp: " + this.timestamp);
    }

    public Instant getTicket() {
        return this.timestamp;
    }

    public double getPrice(int dur) {
        return BigDecimal.valueOf(.10 * dur).setScale(3, RoundingMode.HALF_UP).doubleValue();
    }
}