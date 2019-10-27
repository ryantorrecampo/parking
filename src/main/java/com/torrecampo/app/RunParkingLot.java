package com.torrecampo.app;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class RunParkingLot {

    ArrayList<Group> gr;
    ArrayList<String> sequence;

    public RunParkingLot(ArrayList<Group> groups, ArrayList<String> seq) {
        this.gr = groups;
        this.sequence = seq;
    }

    private boolean spotAvaialble() {
        for (int x = 0; x < gr.size(); x++) {
            ParkingLot lot = gr.get(x).lot;
            if (lot.hasSpots())
                return true;
        }
        return false;
    }

    public void start() {
        Queue<Car> q = new LinkedList<>();
        // Execute sequence
        for (int j = 0; j < sequence.size(); j++) {
            String s = sequence.get(j);
            here: if (q.peek() != null && spotAvaialble()) {
                Car curr = q.remove();
                // Check if car is already in a parking lot
                for (int i = 0; i < gr.size(); i++) {
                    ParkingLot lot = gr.get(i).lot;
                    for (int x = 0; x < lot.parkingSpots.size(); x++) {
                        if (lot.parkingSpots.get(x).isOpen() == false) {
                            String carID = lot.parkingSpots.get(x).car.getID();
                            if (carID.equals(curr.getID())) {
                                System.out.println("Car is already at a lot.");
                                break here;
                            }
                        }
                    }
                }

                ParkingSpot temp = new ParkingSpot();
                Group forDiscount = new Group("temp", new ParkingLot(0, "temp"), 0, 0);

                // Get lot with the least amount of parking spots
                ParkingLot l = new ParkingLot(500000, "temp");
                for (int x = 0; x < gr.size(); x++) {
                    ParkingLot lot = gr.get(x).lot;
                    if (lot.getOpenSpots() < l.getOpenSpots() && lot.getOpenSpots() != 0) {
                        forDiscount = gr.get(x);
                        l = lot;
                    }
                }
                System.out.println("Lot " + l.name + " has the least amount of available spots with " + l.getOpenSpots()
                        + " spots");
                forDiscount.setDiscount(curr);

                // Find open parking spot
                for (int i = 0; i < l.parkingSpots.size(); i++) {
                    if (l.parkingSpots.get(i).isOpen()) {
                        try {
                            temp.parkCar(curr);
                            Thread.sleep(1000);
                            l.parkingSpots.put(i, temp);
                            break here;
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                }
            } else if (s.startsWith("Enters:")) {
                if (s.indexOf(',') < 0) {
                    System.err.println("Invalid format. No duration was given. Please check format and try agan.");
                    System.exit(0);
                }
                String car = s.substring(7, s.indexOf(','));
                int dur = Integer.parseInt(s.substring(s.indexOf(',') + 1));
                Car curr = new Car(car, dur);
                // Check if car is already in a parking lot
                for (int i = 0; i < gr.size(); i++) {
                    ParkingLot lot = gr.get(i).lot;
                    for (int x = 0; x < lot.parkingSpots.size(); x++) {
                        if (lot.parkingSpots.get(x).isOpen() == false) {
                            String carID = lot.parkingSpots.get(x).car.getID();
                            if (carID.equals(car)) {
                                System.out.println("Car is already at a lot.");
                                break here;
                            }
                        }
                    }
                }

                ParkingSpot temp = new ParkingSpot();
                Group forDiscount = new Group("temp", new ParkingLot(0, "temp"), 0, 0);

                // Get lot with the least amount of parking spots
                ParkingLot l = new ParkingLot(500000, "temp");
                for (int x = 0; x < gr.size(); x++) {
                    ParkingLot lot = gr.get(x).lot;
                    if (lot.getOpenSpots() < l.getOpenSpots() && lot.getOpenSpots() != 0) {
                        forDiscount = gr.get(x);
                        l = lot;
                    }
                }
                if (l.name.equals("temp")) {
                    System.out.println("The lot is currently full...Adding " + curr.getID() + " to queue");
                    q.add(curr);
                    break here;
                }
                System.out.println("Lot " + l.name + " has the least amount of available spots with " + l.getOpenSpots()
                        + " spots");
                // System.out.println("Setting Discount for group " + forDiscount.name);
                forDiscount.setDiscount(curr);

                // Find open parking spot
                for (int i = 0; i < l.parkingSpots.size(); i++) {
                    if (l.parkingSpots.get(i).isOpen()) {
                        try {
                            temp.parkCar(curr);
                            Thread.sleep(1000);
                            l.parkingSpots.put(i, temp);
                            break here;
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                }
                System.out.println("The lot is currently full...Adding " + curr.getID() + " to queue");
                q.add(curr);

            } else if (s.startsWith("Exits:")) {
                String car = s.substring(6);
                ParkingSpot temp = new ParkingSpot();
                ParkingLot lot = new ParkingLot(0, "temp");
                Group tempGroup = new Group("temp", new ParkingLot(0, "temp"), 0, 0);
                lotHere: for (int i = 0; i < gr.size(); i++) {
                    lot = gr.get(i).lot;
                    tempGroup = gr.get(i);
                    for (int x = 0; x < lot.parkingSpots.size(); x++) {
                        if (lot.parkingSpots.get(x).isOpen() == false) {
                            String carID = lot.parkingSpots.get(x).car.getID();
                            if (carID.equals(car)) {
                                break lotHere;
                            }
                        }
                    }
                }

                for (int i = 0; i < lot.parkingSpots.size(); i++) {
                    if (!lot.parkingSpots.get(i).isOpen()) {
                        if ((lot.parkingSpots.get(i).car.getID()).equals(car)) {
                            temp = lot.parkingSpots.get(i);
                            try {
                                temp.car.recieveTicket(new Ticket(temp.car.price));
                                double cost = temp.car.ticket.getPrice(temp.car.getDuration());
                                lot.profit += cost; // Car pays the ticket
                                temp.removeCar();
                                // Add car from front of the queue to this parking spot
                                if (q.peek() != null) {
                                    Car tempo = q.remove();
                                    tempGroup.setDiscount(tempo);
                                    temp.parkCar(tempo);
                                }
                                Thread.sleep(1000);
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        }
                    }
                }

            }
        }

        for (int i = 0; i < gr.size(); i++) {
            ParkingLot lot = gr.get(i).lot;
            lot.profit = BigDecimal.valueOf(lot.profit).setScale(2, RoundingMode.HALF_UP).doubleValue();
            System.out.println("The parking lot made a total of $" + lot.profit);
            gr.get(i).getParkingSpots();
        }
    }
}
