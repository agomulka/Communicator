package communicator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReceiverAdapterOut implements AdapterOutput {
    @Override
    public void put(String line) {
        System.out.println(line);
    }
}

class SocketReaderAdapter implements AdapterInput{
    private final BufferedReader br;

    SocketReaderAdapter(Socket socket) throws IOException {
        InputStream input = socket.getInputStream();
        br= new BufferedReader(new InputStreamReader(input));
    }

    @Override
    public String take() throws IOException {
        return br.readLine();
    }
}