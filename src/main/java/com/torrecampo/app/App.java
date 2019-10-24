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
        ArrayList<ParkingLot> lots = new ArrayList<ParkingLot>();
        // ArrayList<ArrayList<String>> sequences = new ArrayList<ArrayList<String>>();

        // Parse input file
        String line;
        while ((line = br.readLine()) != null) {
            if (line.startsWith("Lot:")) {
                int cap = Integer.parseInt(line.substring(6));
                String name = line.substring(4, line.indexOf(','));
                ParkingLot lot = new ParkingLot(cap, name);
                lots.add(lot);
            }
            if (line.startsWith("Enters:") || line.startsWith("Exits:"))
                sequence.add(line);
        }
        br.close();

        for (int i = 0; i < lots.size(); i++) {
            RunParkingLot test = new RunParkingLot(lots.get(i), sequence);
            System.out.println("Running sequence for parking lot: " + lots.get(i).name);
            test.start();
        }
    }

}
