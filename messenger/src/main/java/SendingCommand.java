import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class SendingCommand implements Command{
    Integer port;
    BlockingQueue<String> inputQueue;
    Map<Integer, BlockingQueue<String>> inputMap;

    public SendingCommand(Map<Integer, BlockingQueue<String>> inputMap) {
        this.inputMap = inputMap;
    }

    @Override
    public void execute(String[] myArray) {
        port = Integer.parseInt(myArray[1]);

        StringBuilder builder = new StringBuilder();
        for(int i = 2; i< myArray.length; i++) {
            builder.append(myArray[i]+" ");
        }
        String string = builder.toString();

        inputQueue = inputMap.get(port);
        inputQueue.add(string);
    }

    @Override
    public String description() {
        return "send";
    }
}
