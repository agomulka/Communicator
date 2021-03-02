package communicator;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class EndingCommand implements Command{
    Service service;
    Map<Integer, BlockingQueue<String>> inputMap;

    public EndingCommand(Service service, Map<Integer, BlockingQueue<String>> inputMap) {
        this.service = service;
        this.inputMap = inputMap;
    }

    @Override
    public void execute(String [] myArray) throws IOException {
        System.out.println("connection ");
    }


    public String description(){
        return "#end";
    }

}
