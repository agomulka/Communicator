package communicator;

import java.io.*;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

public class Finisher extends Thread {
    Socket s;
    OutputStream out;
    PrintWriter pw;
    String name;
    Semaphore semaphore;
    Boolean barrier;
    Service service;
    Map<Integer, BlockingQueue<String>> inputMap;

    public Finisher(Socket s, Service service, Map<Integer, BlockingQueue<String>> inputMap) throws IOException {
        this.s = s;
        this.name = service.name;
//        this.semaphore = semaphore;
  //      this.barrier = barrier;
        this.service = service;
//        out = s.getOutputStream();
//        out = new BufferedOutputStream(out);
//        pw = new PrintWriter(new OutputStreamWriter(out));
        this.inputMap = inputMap;
    }

    @Override
    public void run() {
        while(true) {
        //    if(semaphore.tryAcquire()) {
//            if(service.getBarrier()){
            if(service.barrier.get()){
                try {
                    int port = s.getPort();
                    System.out.println("try to disconnect with " + port);
                    s.close();
                    inputMap.remove(port);
                    System.out.println("disconnected.");
                    service.barrier.set(false);

                 //   semaphore.release();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}
