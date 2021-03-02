package communicator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Receiver extends Thread{
    Socket s;
    InputStream input;
    BufferedReader br;
    private AdapterInput in;
    String line;


    public Receiver(AdapterInput in, AdapterOutput out) throws IOException {
        this.in = in;
        this.s = s;
       }

    @Override
    public void run() {
        try {
            while(true) {
               line = in.take();
               System.out.println( line);
               out.put(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
