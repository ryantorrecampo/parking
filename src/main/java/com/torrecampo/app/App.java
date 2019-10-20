package com.torrecampo.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class App {

    public static void main(String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader br = new BufferedReader(new FileReader(file.getAbsoluteFile()));
        int capacity = 0;
        ArrayList<String> sequence = new ArrayList<String>();

        // Parse input file
        String line;
        while ((line = br.readLine()) != null) {
            if (line.startsWith("Capacity:"))
                capacity = Integer.parseInt(line.substring(9));
            if (line.startsWith("Enters:") || line.startsWith("Exits:"))
                sequence.add(line);
        }
        br.close();

        // Create the parking lot
        ParkingLot lot = new ParkingLot(capacity);

        Queue<Car> q = new LinkedList<>();
        // Execute sequence
        for (int j = 0; j < sequence.size(); j++) {
            String s = sequence.get(j);
            // System.out.println(s);
            // System.out.println(j);
            here: if (q.peek() != null && lot.hasSpots()) {
                Car curr = q.remove();
                ParkingSpot temp = new ParkingSpot();
                // Find open parking spot
                for (int i = 0; i < lot.parkingSpots.size(); i++) {
                    if (lot.parkingSpots.get(i).isOpen()) {
                        System.out.println();
                        try {
                            temp.parkCar(curr);
                            Thread.sleep(1000);
                            lot.parkingSpots.put(i, temp);
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
                ParkingSpot temp = new ParkingSpot();
                // Find open parking spot
                for (int i = 0; i < lot.parkingSpots.size(); i++) {
                    if (lot.parkingSpots.get(i).isOpen()) {
                        try {
                            temp.parkCar(curr);
                            Thread.sleep(1000);
                            lot.parkingSpots.put(i, temp);
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
                for (int i = 0; i < lot.parkingSpots.size(); i++) {
                    if (!lot.parkingSpots.get(i).isOpen()) {
                        if ((lot.parkingSpots.get(i).car.getID()).equals(car)) {
                            temp = lot.parkingSpots.get(i);
                            try {
                                temp.car.recieveTicket(new Ticket());
                                double cost = temp.car.ticket.getPrice(temp.car.getDuration());
                                lot.profit += cost; // Car pays the ticket
                                temp.removeCar();
                                if (q.peek() != null) {
                                    temp.parkCar(q.remove());
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

        lot.profit = BigDecimal.valueOf(lot.profit).setScale(4, RoundingMode.HALF_UP).doubleValue();
        System.out.println("The parking lot made a total of $" + lot.profit);
    }

}
