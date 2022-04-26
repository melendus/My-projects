package BusinessLogic;

import Model.Server;
import Model.Task;
import Util.WriterToFile;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SimulationManager  implements Runnable{
    public int timeLimit; // maximum processing time - read from UI
    public int maxProcessingTime;
    public int minProcessingTime;
    public int maxArrivalTime;
    public int minArrivalTime;
    public int numberOfServers;
    public int numberOfClients;


    private String outputString;

    public SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_TIME;

    private Scheduler scheduler;  // entity responsible with    queue management and client distribution

    private List<Task> generatedTasks = new ArrayList<>();

    private TextArea textArea;

    public SimulationManager(int timeLimit, int maxProcessingTime, int minProcessingTime, int maxArrivalTime, int minArrivalTime, int numberOfServers, int numberOfClients, TextArea textArea) {
        this.timeLimit = timeLimit;
        this.maxProcessingTime = maxProcessingTime;
        this.minProcessingTime = minProcessingTime;
        this.maxArrivalTime = maxArrivalTime;
        this.minArrivalTime = minArrivalTime;
        this.numberOfServers = numberOfServers;
        this.numberOfClients = numberOfClients;
        this.outputString = "";
        this.textArea = textArea;
        scheduler = new Scheduler(numberOfServers, 100);
        scheduler.changeStrategy(selectionPolicy);
        generateNRandomTasks();

        for (Server server : scheduler.getServers()) {
            Thread t = new Thread(server);
            t.start();
        }
    }

    private void generateNRandomTasks() {
        Random random = new Random();
        for (int i = 0; i < numberOfClients; i++) {
            int arrivalTime = random.nextInt(maxArrivalTime - minArrivalTime) + minArrivalTime;
            int serviceTime = random.nextInt(maxProcessingTime - minProcessingTime) + minProcessingTime;
            Task task = new Task(i, arrivalTime, serviceTime);
            generatedTasks.add(task);
        }
        Collections.sort(generatedTasks);
    }

    @Override
    public void run() {
        int currentTime = 0;
        boolean cond = true;

        int totalServiceTime = 0;
        int totalWaitingTime = 0;

        int peakHour = 0;
        int maxTasks = 0;

        while (currentTime < timeLimit && cond) {
            String string = "";
            System.out.println("Time " + currentTime);
            string += "Time " + currentTime + "\n";
            List<Task> toDispatch = new ArrayList<>();
            for (Task task : generatedTasks) {
                if (task.getArrivalTime() == currentTime) {
                    toDispatch.add(task);
                }
            }
            generatedTasks.removeAll(toDispatch);

            for (Task task : toDispatch) {
                try {
                    scheduler.dispatchTask(task);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //tasks run()

            System.out.print("Waiting clients: ");
            string += "Waiting clients: ";

            for (Task task : generatedTasks) {
                System.out.print("(" + task.getID() + " " + task.getArrivalTime() + " " + task.getServiceTime() + ") ");
                string += "(" + task.getID() + " " + task.getArrivalTime() + " " + task.getServiceTime() + ") ";
            }

            string += "\n";

            System.out.println();
            int totalClientsInTheQ = 0;
            int tasksTotal = 0;
            for (Server server : this.scheduler.getServers()) {
                tasksTotal += server.getTasks().size();
                String ceva = server.displayTasks();
                string += ceva;
                totalClientsInTheQ += server.getTasks().size();
            }
            if (tasksTotal > maxTasks) {
                maxTasks = tasksTotal;
                peakHour = currentTime;
            }

            if (totalClientsInTheQ == 0 && generatedTasks.size() == 0) {
                cond = false;
            }
            outputString += string;
            textArea.setText(outputString);

            currentTime++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (Server server : scheduler.getServers()) {
            server.stop();
        }

        for (Server server : scheduler.getServers()) {
            totalServiceTime += server.getTotalServiceTime().intValue();
            totalWaitingTime += server.getTotalWaitingTime().intValue();
        }

        System.out.println("Average waiting time = " + (float) totalWaitingTime / this.numberOfClients);
        System.out.println("Average service time = " + (float) totalServiceTime / this.numberOfClients);
        System.out.println("Peak hour is: " + peakHour);
        outputString += "\nPeak hour is: " + peakHour + "\n";
        outputString += "Average waiting time = " + (float) totalWaitingTime / this.numberOfClients + "\n";
        outputString += "Average service time = " + (float) totalServiceTime / this.numberOfClients + "\n";
    }

    public String getOutputString() {
        return outputString;
    }
}
