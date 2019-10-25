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
        this.discount = d;
    }

    public void getInfo() {
        System.out.println("Price: " + this.price);
        System.out.println("Discount: " + this.discount);
    }

    public void setDiscount(Car c) {
        if (asianCars.contains(c.ID)) {

        }
    }
}