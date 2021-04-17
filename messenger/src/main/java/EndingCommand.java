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
    public void execute(String [] myArray) {
        System.out.println("disconnecting...  ");
        service.barrier.set(true);
        service.b2.set(true);
    }


    public String description(){
        return "#end";
    }

}
