import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Semaphore;

public class MyServer {

    public static void main(String[] args) throws Exception {
        runServer();
    }

    public static void runServer() throws Exception {
        ServerSocket ss = new ServerSocket(8080);
        Semaphore semaphore = new Semaphore(2);
        try {
            while (true) {
                if(semaphore.tryAcquire()) {
                    Socket s = ss.accept();
                    System.out.println("connect");
                    try {
                        Thread clientHandler = new ClientHandler(s, semaphore);
                        clientHandler.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } finally {
            ss.close();
        }
    }
}


class ClientHandler extends Thread {
    Socket s;
    Semaphore semaphore;
    private static final String ENCODING = "ISO-8859-1";

    public ClientHandler(Socket s, Semaphore semaphore) throws IOException, Exception {
        this.s = s;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            InputStream in = s.getInputStream();
            OutputStream out = s.getOutputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in, ENCODING));
            out = new BufferedOutputStream(out);
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(out, ENCODING));

            String line = br.readLine();
            int ixSpace = line.indexOf(' ');
            if (ixSpace == -1)
                throw new Exception("Illegal syntax");
            String command = line.substring(0, ixSpace).trim();
            double d = Double.parseDouble(line.substring(ixSpace + 1).trim());

            Thread.sleep(15000);

            double result;
            if ("SQRT".equalsIgnoreCase(command)) {
                result = Math.sqrt(d);
            } else if ("SIN".equalsIgnoreCase(command)) {
                result = Math.sin(d);
            } else {
                throw new Exception("Unrecognised command '" + command + "'");
            }
            pw.println("RESULT: " + result);
            pw.flush();
            pw.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            try {
                s.close();
                semaphore.release();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
