package communicator;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;


public class Sender extends Thread{
    Socket s;
    OutputStream out;
    PrintWriter pw;
    String name;
    BlockingQueue<String> input;

    public Sender(Socket s, String name, BlockingQueue<String> inputQueue) throws IOException {
        this.s = s;
        this.name = name;
        input = inputQueue;
        out = s.getOutputStream();
        out = new BufferedOutputStream(out);
        pw = new PrintWriter(new OutputStreamWriter(out));
    }

    @Override
    public void run() {
        try {

            while(true) {
                String string = input.take();
                pw.println(name + ": "+string);
                pw.flush();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
