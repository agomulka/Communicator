import java.io.IOException;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class Finisher extends Thread {
    Socket s;
    String name;
    Service service;
    Map<Integer, BlockingQueue<String>> inputMap;


    public Finisher(Socket s, Service service, Map<Integer, BlockingQueue<String>> inputMap){
        this.s = s;
        this.name = service.name;
        this.service = service;
        this.inputMap = inputMap;
    }

    @Override
    public void run() {
        while(true) {
            if(service.barrier.get()){
                try {
                    int port = s.getPort();
                    System.out.println("try to disconnect with " + port);
                    s.close();
                    inputMap.remove(port);
                    System.out.println("disconnected.");
                    service.barrier.set(false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}