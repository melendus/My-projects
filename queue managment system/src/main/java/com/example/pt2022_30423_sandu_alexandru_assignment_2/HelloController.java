package com.example.pt2022_30423_sandu_alexandru_assignment_2;

import BusinessLogic.SimulationManager;
import Util.SleepService;
import Util.WriterToFile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.IOException;

public class HelloController {

    @FXML
    private TextField maxArrivalTime;

    @FXML
    private TextField maxServiceTime;

    @FXML
    private TextField maxTime;

    @FXML
    private TextField minArrivalTime;

    @FXML
    private TextField minServiceTime;

    @FXML
    private TextField nrOfClients;

    @FXML
    private TextField nrOfQueues;

    @FXML
    private Button restartButton;

    @FXML
    private Button startButton;

    @FXML
    private TextArea textArea;

    @FXML
    void getDataAndStartSim(ActionEvent event) throws IOException, InterruptedException {
        if (nrOfClients.getText().isEmpty()) {
            if (!(nrOfClients.getText().matches("[0-9]+") && nrOfClients.getText().length() > 1)) {
                textArea.setText("INVALID INPUT");
            }
        }
        else if (nrOfQueues.getText().isEmpty()) {
            if (!(nrOfQueues.getText().matches("[0-9]+") && nrOfQueues.getText().length() > 1)) {
                textArea.setText("INVALID INPUT");
            }
        }

        else if (maxTime.getText().isEmpty()) {
            if (!(maxTime.getText().matches("[0-9]+") && maxTime.getText().length() > 1)) {
                textArea.setText("INVALID INPUT");
            }
        }

        else if (minArrivalTime.getText().isEmpty()) {
            if (!(minArrivalTime.getText().matches("[0-9]+") && minArrivalTime.getText().length() > 1)) {
                textArea.setText("INVALID INPUT");
            }
        }

        else if (maxArrivalTime.getText().isEmpty()) {
            if (!(maxArrivalTime.getText().matches("[0-9]+") && maxArrivalTime.getText().length() > 1)) {
                textArea.setText("INVALID INPUT");
            }
        }

        else if (minServiceTime.getText().isEmpty()) {
            if (!(minServiceTime.getText().matches("[0-9]+") && minServiceTime.getText().length() > 1)) {
                textArea.setText("INVALID INPUT");
            }
        }
        else if (maxServiceTime.getText().isEmpty()) {
            if (!(maxServiceTime.getText().matches("[0-9]+") && maxServiceTime.getText().length() > 1)) {
                textArea.setText("INVALID INPUT");
            }
        } else {
            int timeLimit;
            int maxProcessingTime;
            int minProcessingTime;
            int maxArrivalTimeNumber;
            int minArrivalTimeNumber;
            int numberOfServersNumber;
            int numberOfClientsNumber;

            timeLimit = Integer.parseInt(maxTime.getText());
            maxProcessingTime = Integer.parseInt(maxServiceTime.getText());
            minProcessingTime = Integer.parseInt(minServiceTime.getText());
            maxArrivalTimeNumber = Integer.parseInt(maxArrivalTime.getText());
            minArrivalTimeNumber = Integer.parseInt(minArrivalTime.getText());
            numberOfServersNumber = Integer.parseInt(nrOfQueues.getText());
            numberOfClientsNumber = Integer.parseInt(nrOfClients.getText());
            SimulationManager simulationManager = new SimulationManager(timeLimit, maxProcessingTime, minProcessingTime, maxArrivalTimeNumber, minArrivalTimeNumber, numberOfServersNumber, numberOfClientsNumber, textArea);
           /* WriterToFile fileWriter = new WriterToFile("C:\\Users\\mosh_\\Desktop\\cursuri\\anul2\\sem2\\PT\\labs\\PT2022_30423_Sandu_Alexandru_Assignment_2\\src\\output.txt");




            Thread t = new Thread(simulationManager);
            t.start();
            t.join();

            fileWriter.writeToFile(simulationManager.getOutputString());

            textArea.setText(simulationManager.getOutputString());*/
            SleepService sleepService = new SleepService(simulationManager, textArea);
            sleepService.start();
        }
    }
}
