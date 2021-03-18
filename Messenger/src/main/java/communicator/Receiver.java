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
    private AdapterOutput out;
    String line;


    public Receiver(AdapterInput in, AdapterOutput out){
        this.in = in;
        this.out = out;
     //   this.s = s;
       }

    @Override
    public void run() {
        try {
            while(true) {
               line = in.take();
            //   System.out.println(line);
               out.put(line);  //co to robi?
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
