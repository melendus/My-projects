package Util;

import BusinessLogic.SimulationManager;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;

import java.io.IOException;

public class SleepService extends Service<String> {

    private SimulationManager simulationManager;
    private TextArea textArea;
    private WriterToFile fileWriter;


    public SleepService(SimulationManager simulationManager, TextArea textArea) throws IOException {
        this.simulationManager = simulationManager;
        fileWriter = new WriterToFile("C:\\Users\\mosh_\\Desktop\\cursuri\\anul2\\sem2\\PT\\labs\\PT2022_30423_Sandu_Alexandru_Assignment_2\\src\\output.txt");
        this.textArea = textArea;
        setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                textArea.setText(simulationManager.getOutputString());
            }
        });
    }

    @Override
    protected Task<String> createTask() {
        return new Task<String>() {
            @Override
            protected String call() throws Exception {
                Thread t = new Thread(simulationManager);
                t.start();
                //textArea.setText(simulationManager.getOutputString());
                t.join();

                fileWriter.writeToFile(simulationManager.getOutputString());
                return null;
            }
        };
    }
}
