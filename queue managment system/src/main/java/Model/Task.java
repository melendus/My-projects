package Model;

import java.util.Objects;

public class Task implements Comparable<Task>{
    private int ID;
    private int arrivalTime;
    private int serviceTime;

    public Task(int ID, int arrivalTime, int serviceTime) {
        this.ID = ID;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    public synchronized void decrementServiceTime() {
        this.serviceTime--;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    @Override
    public int compareTo(Task t) {
        if (this.getArrivalTime() > t.getArrivalTime()) {
            return 1;
        } else if (this.getArrivalTime() < t.getArrivalTime())
            return -1;
        else {
            if (this.getServiceTime() > t.getServiceTime()) {
                return 1;
            } else if (this.getServiceTime() < t.getServiceTime())
                return -1;
            else
                return 0;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return ID == task.ID && arrivalTime == task.arrivalTime && serviceTime == task.serviceTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, arrivalTime, serviceTime);
    }
}
