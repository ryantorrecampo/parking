package com.torrecampo.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Group {
    double price;
    double discount;
    ParkingLot lot;
    String name;
    List<String> asianCars = new ArrayList<>(Arrays.asList("Acura", "Honda", "Lexus"));
    List<String> americanCars = new ArrayList<>(Arrays.asList("Chevy", "Ford"));

    public Group(String groupName, ParkingLot lot, double p, double d) {
        this.lot = lot;
        this.name = groupName;
        this.price = p;
        this.discount = 1 - d;
    }

    public void getInfo() {
        // Cars can inquire latest price/send latest prices to interested cars
        System.out.println("Price: " + this.price);
        System.out.println("Discount: " + this.discount);
    }

    public void setDiscount(Car c) {
        if (asianCars.contains(c.ID) && name.equals("Asian")) {
            c.price = price * discount;
            System.out.println("Discount for Asian cars applied!");
        } else if (americanCars.contains(c.ID) && name.equals("American")) {
            c.price = price * discount;
            System.out.println("Discount for American cars applied!");
        } else if (name.equals("Free")) {
            c.price = 0;
            System.out.println("This garage is free of charge!");
        } else {
            c.price = price;
        }
    }

    public void getParkingSpots() {
        // Cars can inquire availability of parking space
        System.out.println("There are " + lot.getOpenSpots() + " spots available.");
    }
}