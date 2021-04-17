import java.io.IOException;
import java.net.SocketException;

public class Receiver extends Thread{
    private AdapterInput in;
    private AdapterOutput out;
    String line;
    Service service;


    public Receiver(AdapterInput in, AdapterOutput out, Service service){
        this.in = in;
        this.out = out;
        this.service = service;
    }

    @Override
    public void run() {
        try {
            while(!service.b2.get()) {
                line = in.take();
                out.put(line);
            }
        } catch (InterruptedException | SocketException e ) {
            e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
