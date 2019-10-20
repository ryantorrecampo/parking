package com.torrecampo.app;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final int capacity;
    public Map<Integer, ParkingSpot> parkingSpots;
    EnterGate entrance;
    ExitGate exit;
    double profit;

    public ParkingLot(int cap) {
        this.capacity = cap;
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