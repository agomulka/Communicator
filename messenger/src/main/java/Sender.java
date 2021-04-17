import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class Sender extends Thread{
    Socket s;
    OutputStream out;
    PrintWriter pw;
    String name;
    AdapterInput input;
    Service service;

    public Sender(AdapterInput adapterInput, Socket s, Service service) throws IOException {
        this.s = s;
        this.name = service.name();
        input = adapterInput;
        out = s.getOutputStream();
        out = new BufferedOutputStream(out);
        pw = new PrintWriter(new OutputStreamWriter(out));
        this.service = service;
    }

    @Override
    public void run() {
        try {
            while(!service.b2.get()) {
                String string = input.take();
                pw.println(name + ": "+string);
                pw.flush();
            }
        } catch (InterruptedException | SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
