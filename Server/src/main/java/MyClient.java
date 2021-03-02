import javax.print.DocFlavor;
import java.io.*;
import java.net.Socket;

public class MyClient {
    public static void main(String[] args) throws IOException {
        runClient();
    }

    private static void runClient() throws IOException {
        String hostname = "localhost";
        int port = 8080;

        Socket socket = new Socket(hostname, port);
        System.out.println("client here, connect to server");

        InputStream in = System.in;
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line = br.readLine();

        OutputStream out = socket.getOutputStream();
        out = new BufferedOutputStream(out);
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));
        pw.println(line);
        pw.flush();

        InputStream inputFromServer = socket.getInputStream();
        BufferedReader readerFromServer = new BufferedReader(new InputStreamReader(inputFromServer));
        String lineFromServer = readerFromServer.readLine();
        System.out.println(lineFromServer);

        socket.close();

    }
}
