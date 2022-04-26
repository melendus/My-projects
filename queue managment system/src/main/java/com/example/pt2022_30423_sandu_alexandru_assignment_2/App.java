package com.example.pt2022_30423_sandu_alexandru_assignment_2;

import BusinessLogic.SimulationManager;
import Util.WriterToFile;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws InterruptedException, IOException {
        HelloApplication.main(args);
        /*SimulationManager simulationManager = new SimulationManager();
        WriterToFile fileWriter = new WriterToFile("C:\\Users\\mosh_\\Desktop\\cursuri\\anul2\\sem2\\PT\\labs\\PT2022_30423_Sandu_Alexandru_Assignment_2\\src\\output.txt");

        Thread t = new Thread(simulationManager);
        t.start();
        t.join();

        fileWriter.writeToFile(simulationManager.getOutputString());*/
    }
}
