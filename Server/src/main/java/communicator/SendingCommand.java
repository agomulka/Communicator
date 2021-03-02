package communicator;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class SendingCommand implements Command{
    Integer port;
    String string;
    BlockingQueue<String> inputQueue;
    Map<Integer, BlockingQueue<String>> inputMap;

    public SendingCommand(Map<Integer, BlockingQueue<String>> inputMap) {
        this.inputMap = inputMap;
    }

    @Override
    public void execute(String[] myArray) throws IOException {
        port = Integer.parseInt(myArray[1]);
        string = myArray[2];
        inputQueue = inputMap.get(port);
        inputQueue.add(string);
    }

    @Override
    public String description() {
        return "send";
    }
}
