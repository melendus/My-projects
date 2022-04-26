package BusinessLogic;

import Model.Server;
import Model.Task;

import java.util.List;

public class TimeStrategy implements Strategy {
    @Override
    public synchronized void addTask(List<Server> servers, Task t) throws InterruptedException {
        Server myLittleServer = servers.get(0);

        for(Server server : servers) {
            if (server.getWaitingPeriod().get() <= myLittleServer.getWaitingPeriod().get()) {
                myLittleServer = server;
            }
        }
        myLittleServer.addTask(t);
    }
}
