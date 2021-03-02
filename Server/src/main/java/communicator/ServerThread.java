package communicator;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ServerThread extends Thread {
    ServerSocket ss;
    Integer port;
    Invoker invoker;
    String name;
    Service service;
    List<Object> list;
    Map<Integer, BlockingQueue<String>> inputMap;

    public ServerThread(Service service, String name, Integer port, Map<Integer, BlockingQueue<String>> inputMap) throws IOException {
        this.service = service;
        this.name = name;
        this.port = port;
        this.inputMap = inputMap;
    }

    @Override
    public void run() {
        try {
            ss = new ServerSocket(port);
            while (true) {
                Socket s = ss.accept();
                System.out.println("connection established "+s.getInetAddress()+" "+s.getPort());
                BlockingQueue<String> serverInputQueue = new LinkedBlockingQueue<>();
                inputMap.put(s.getPort(), serverInputQueue);
                Thread receiver = new Receiver(s);
                Thread sender = new Sender(s, service.name(), serverInputQueue);
                receiver.start();
                sender.start();
                    }

            //  }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
