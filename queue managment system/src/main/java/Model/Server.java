package Model;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {
    private final BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;
    private volatile boolean isRunning = false;
    private int queueNr;
    private AtomicInteger totalWaitingTime;
    private AtomicInteger totalServiceTime;
    private AtomicInteger nrTasks;

    public Server(int capacity, int queueNr) {
        waitingPeriod = new AtomicInteger(0);
        totalWaitingTime = new AtomicInteger(0);
        totalServiceTime = new AtomicInteger(0);
        nrTasks = new AtomicInteger(0);
        tasks = new ArrayBlockingQueue<>(capacity);
        this.queueNr = queueNr;

        start();
    }

    public Server(BlockingQueue<Task> tasks, AtomicInteger waitingPeriod) {
        this.tasks = tasks;
        this.waitingPeriod = waitingPeriod;
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                if (tasks.size() != 0) {
                    Task task = tasks.peek();
                    //Thread.sleep(1000);
                    Thread.sleep(task.getServiceTime() * 1000L);
                    //task.decrementServiceTime();
                    //this.waitingPeriod.decrementAndGet();

                    if (tasks.peek().getServiceTime() == 0) {
                        //Thread.sleep(1000);
                        tasks.take();
                    }
                } else
                    Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Task> getTasks() {
        return tasks.stream().toList();
    }

    public void start() {
        isRunning = true;
    }

    public void stop() {
        isRunning = false;
    }

    public synchronized void addTask(Task newTask) throws InterruptedException {
        tasks.put(newTask);
        totalServiceTime.addAndGet(newTask.getServiceTime());
        if (waitingPeriod.intValue() != 0) {
            totalWaitingTime.addAndGet(waitingPeriod.intValue());
        }
        nrTasks.incrementAndGet();
        this.waitingPeriod.addAndGet(newTask.getServiceTime());
    }

    public synchronized AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public synchronized AtomicInteger getTotalWaitingTime() {
        return totalWaitingTime;
    }

    public synchronized AtomicInteger getTotalServiceTime() {
        return totalServiceTime;
    }

    public String displayTasks() {
        String outputString = "";
        System.out.print("Queue " + this.queueNr + ": ");
        outputString = outputString + "Queue " + this.queueNr + ": ";
        if (tasks.size() == 0) {
            System.out.println("closed");
            outputString += "closed";
        } else {
            for (Task task : tasks) {
                outputString += "(" + task.getID() + " " + task.getArrivalTime() + " " + task.getServiceTime() + ")";
                System.out.print("(" + task.getID() + " " + task.getArrivalTime() + " " + task.getServiceTime() + ")");
            }
            System.out.println();
        }
        Task task = tasks.peek();
        if (task != null) {
            task.decrementServiceTime();
            this.waitingPeriod.decrementAndGet();
        }

        outputString += "\n";
        return outputString;
    }
}
