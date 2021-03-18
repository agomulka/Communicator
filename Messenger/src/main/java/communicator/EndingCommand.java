package communicator;

import java.io.IOException;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

public class EndingCommand implements Command{
    Service service;
    Integer port;
    Map<Integer, BlockingQueue<String>> inputMap;
//    Semaphore semaphore;
//    Boolean barrier;

    public EndingCommand(Service service, Map<Integer, BlockingQueue<String>> inputMap) {
        this.service = service;
        this.inputMap = inputMap;
      //  this.semaphore = semaphore;
//        this.barrier = barrier;
    }

    @Override
    public void execute(String [] myArray) throws IOException, InterruptedException {
        System.out.println("disconnecting...  ");
      //  semaphore.acquire();
//        service.setBarrier(true);
        service.barrier.set(true);
    }


    public String description(){
        return "#end";
    }

}
