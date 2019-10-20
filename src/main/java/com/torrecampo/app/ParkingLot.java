package com.torrecampo.app;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final int capacity;
    public Map<Integer, ParkingSpot> parkingSpaces;

    public ParkingLot(int cap) {
        this.capacity = cap;
        parkingSpaces = new HashMap<Integer, ParkingSpot>(this.capacity);
        for (int i = 0; i < capacity; i++) {
            parkingSpaces.put(i, new ParkingSpot());
        }
    }

}