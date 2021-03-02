package communicator;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class ConnectingCommand implements Command {
    Map<Integer, BlockingQueue<String>> inputMap;
    Service service;
    String port;

    public ConnectingCommand(Service service, Map<Integer, BlockingQueue<String>> inputMap) {
        this.service = service;
        this.inputMap = inputMap;
    }

    @Override
    public void execute(String[] array) throws IOException {
        Thread clientThread = new ClientThread(service, array, inputMap);
        clientThread.start();
    }

    public String description(){
        return "connect";
    }
}
