package communicator;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class PortPrintingCommand implements Command{

    Map<Integer, BlockingQueue<String>> inputMap;

    public PortPrintingCommand(Map<Integer, BlockingQueue<String>> inputMap) {
        this.inputMap = inputMap;
    }

    @Override
    public void execute(String[] myArray) throws IOException {
        for(Integer port : inputMap.keySet()){
            System.out.println(port);
        }
    }

    @Override
    public String description() {
        return "print";
    }
}
