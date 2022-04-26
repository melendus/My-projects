package BusinessLogic;

import Model.Server;
import Model.Task;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private List<Server> servers;
    private int maxNoServers;
    private int maxTasksPerServer;
    private Strategy strategy;

    public Scheduler(int maxNoServers, int maxTasksPerServer) {
        servers = new ArrayList<>(maxNoServers);
        for (int i = 0; i < maxNoServers; i++) {
            Server newServer = new Server(maxTasksPerServer, i + 1);
            servers.add(newServer);
        }
    }

    public void changeStrategy(SelectionPolicy policy) {
        if (policy == SelectionPolicy.SHORTEST_QUEUE) {
            strategy = new ShortestQueueStrategy();
        } else if (policy == SelectionPolicy.SHORTEST_TIME) {
            strategy = new TimeStrategy();
        }
    }

    public synchronized void dispatchTask(Task task) throws InterruptedException {
        strategy.addTask(servers, task);
    }

    public synchronized List<Server> getServers() {
        return servers;
    }

}
