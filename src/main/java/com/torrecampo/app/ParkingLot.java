package com.torrecampo.app;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final int capacity;
    public Map<Integer, ParkingSpot> parkingSpots;
    EnterGate entrance;
    ExitGate exit;
    double profit;
    String name;

    public ParkingLot(int cap, String name) {
        this.capacity = cap;
        this.name = name;
        parkingSpots = new HashMap<Integer, ParkingSpot>(this.capacity);
        for (int i = 0; i < capacity; i++) {
            parkingSpots.put(i, new ParkingSpot());
        }
    }

    public boolean hasSpots() {
        for (int i = 0; i < parkingSpots.size(); i++) {
            if (parkingSpots.get(i).isOpen())
                return true;
        }
        return false;
    }

}