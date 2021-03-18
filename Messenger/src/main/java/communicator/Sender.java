package communicator;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;


public class Sender extends Thread{
    Socket s;
    OutputStream out;
    PrintWriter pw;
    String name;
    AdapterInput input;

    public Sender(AdapterInput adapterInput, Socket s, String name) throws IOException {
        this.s = s;
        this.name = name;
        input = adapterInput;
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
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
