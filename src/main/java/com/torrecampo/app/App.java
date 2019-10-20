package com.torrecampo.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App {
    protected static int capacity;
    public static int numCars = 0;

    public static void main(String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader br = new BufferedReader(new FileReader(file.getAbsoluteFile()));

        ArrayList<String> carIDs = new ArrayList<String>();
        Map<String, Car> cars = new HashMap<String, Car>();
        ArrayList<Integer> durations = new ArrayList<Integer>();
        ArrayList<String> sequence = new ArrayList<String>();
        ArrayList<Car> parkingSpaces = new ArrayList<Car>(capacity);

        String line;
        while ((line = br.readLine()) != null) {
            if (line.startsWith("Capacity:"))
                capacity = Integer.parseInt(line.substring(9));
            if (line.startsWith("ID:"))
                carIDs.add(line.substring(3));
            if (line.startsWith("Duration:"))
                durations.add(Integer.parseInt(line.substring(9)));
            if (line.startsWith("Enters:") || line.startsWith("Exits:"))
                sequence.add(line);
        }
        br.close();

        System.out.println("Parking Lot Capacity: " + capacity);

        // create list of cars
        for (int i = 0; i < carIDs.size(); i++) {
            cars.put(carIDs.get(i), new Car(carIDs.get(i), durations.get(i)));
        }

        for (Car obj : cars.values()) {
            obj.recieveTicket(new Ticket());
            double cost = obj.ticket.getPrice(obj.getDuration());
            System.out.println("This ticket costs " + cost + " for car " + obj.getID());
        }

        for (String s : sequence) {
            if (s.startsWith("Enters:")) {
                String car = s.substring(7);
                parkingSpaces.add(cars.get(car));
                numCars += 1;
            }
        }

        System.out.println("There are " + numCars + " cars currently in the parking lot.");

        for (Car obj : parkingSpaces) {
            System.out.println(obj.getID() + " will be here for " + obj.getDuration());
        }

        // for (int i = 0; i < cars.size(); i++) {
        // Car obj = cars.get(i);
        // obj.recieveTicket(new Ticket());
        // obj.ticket.printTicketInfo();
        // }

        // Ticket test = new Ticket();
        // Car one = new Car("12345");
        // one.recieveTicket(test);
        // one.ticket.printTicketInfo();
        // if (one.hasTicket())
        // System.out.println("Car " + one.ID + " has a ticket");

    }
}
