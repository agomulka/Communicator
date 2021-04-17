import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ClientThread extends Thread{
    Integer port;
    Service service;
    String[] myArray;
    Map<Integer, BlockingQueue<String>> inputMap;

    public  ClientThread(Service service, String[] myArray, Map<Integer, BlockingQueue<String>> inputMap ) throws IOException {
        this.service = service;
        this.myArray = myArray;
        this.inputMap = inputMap;
    }

    @Override
    public void run() {
        try {
            port = Integer.parseInt(myArray[1]);
            String hostname = "localhost";
            Socket socket = new Socket(hostname, port);
            BlockingQueue<String> clientInputQueue = new LinkedBlockingQueue<>();
            inputMap.put(socket.getPort(), clientInputQueue);
            System.out.println("connection established "+socket.getInetAddress()+" "+socket.getPort());

            class QueueReaderAdapter implements AdapterInput {

                @Override
                public String take() throws IOException, InterruptedException {
                    return clientInputQueue.take();
                }
            }

            AdapterInput in = () -> clientInputQueue.take();
            AdapterOutput adapterOut = new ReceiverAdapterOut();
            SocketReaderAdapter adapterIn = new SocketReaderAdapter(socket);
            Thread receiver = new Receiver(adapterIn, adapterOut, service);
            Thread sender = new Sender(in, socket, service);
            Thread finisher = new Finisher(socket, service, inputMap);
            receiver.start();
            sender.start();
            finisher.start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}