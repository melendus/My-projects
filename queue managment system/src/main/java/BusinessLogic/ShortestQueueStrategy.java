package BusinessLogic;

import Model.Server;
import Model.Task;

import java.util.List;

public class ShortestQueueStrategy implements Strategy {
    @Override
    public void addTask(List<Server> servers, Task t) throws InterruptedException {
        Server myLittleServer = servers.get(0);

        for(Server server : servers) {
            if (server.getTasks().size() < myLittleServer.getTasks().size())
                myLittleServer = server;
        }
        myLittleServer.addTask(t);
    }
}
