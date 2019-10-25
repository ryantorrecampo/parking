package com.torrecampo.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class App {

    public static void main(String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader br = new BufferedReader(new FileReader(file.getAbsoluteFile()));
        ArrayList<String> sequence = new ArrayList<String>();
        ArrayList<Group> groups = new ArrayList<Group>();

        // Parse input file
        String line;
        while ((line = br.readLine()) != null) {
            if (line.startsWith("Group:")) {
                String groupName = line.substring(6, line.indexOf('|'));
                String name = line.substring(line.indexOf('|') + 1, line.indexOf('#'));
                int cap = Integer.parseInt(line.substring(line.indexOf('#') + 1, line.indexOf("$")));
                double price = Double.parseDouble(line.substring(line.indexOf('$') + 1, line.indexOf('%')));
                double discount = Double.parseDouble(line.substring(line.indexOf('%') + 1));

                ParkingLot lot = new ParkingLot(cap, name);
                Group gr = new Group(groupName, lot, price, discount);
                groups.add(gr);
            }
            if (line.startsWith("Enters:") || line.startsWith("Exits:"))
                sequence.add(line);
        }
        br.close();

        for (int i = 0; i < groups.size(); i++) {
            RunParkingLot test = new RunParkingLot(groups.get(i), sequence);
            // groups.get(i).getInfo();
            System.out.println("Running sequence for parking lot group: " + groups.get(i).name);
            test.start();
        }
    }

}
