import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ServerThread extends Thread {
    ServerSocket ss;
    Integer port;
    String name;
    Service service;
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
                service.b2.set(false);
                System.out.println("connection established "+s.getInetAddress()+" "+s.getPort());
                BlockingQueue<String> serverInputQueue = new LinkedBlockingQueue<>();
                inputMap.put(s.getPort(), serverInputQueue);
                AdapterInput in = serverInputQueue::take;
                AdapterOutput adapterOut = new ReceiverAdapterOut();
                Thread receiver = new Receiver(new SocketReaderAdapter(s), adapterOut, service);
                Thread sender = new Sender(in, s, service);
                Thread finisher = new Finisher(s, service, inputMap);
                receiver.start();
                sender.start();
                finisher.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
