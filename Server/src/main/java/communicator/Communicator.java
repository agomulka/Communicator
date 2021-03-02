package communicator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.concurrent.*;

public class Communicator {


    public static void main(String[] args) throws Exception {
        Service service = new Service();
        String name = getString(service);
        int port = getPort();

        BlockingQueue<String> inputQueue = new LinkedBlockingQueue<>();

        Map<Integer, BlockingQueue<String>> inputMap = new ConcurrentHashMap<>();

        Thread serverThread = new ServerThread(service, name, port, inputMap);
        serverThread.start();

        Command command1 = new ConnectingCommand(service, inputMap);
        Command command2 = new EndingCommand(service, inputMap);
        Command command3 = new SendingCommand(inputMap);
        Command command4 = new PortPrintingCommand(inputMap);
        Invoker invoker = new Invoker(name, port, command1, command2, command3, command4);
        invoker.startMenu();

        InputStream input = System.in;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
        String line;
        while(true){
            line = bufferedReader.readLine();
            invoker.invoke(line);
        }
    }

    private static int getPort() {
        Integer min = 8075;
        Integer max = 9090;
        int port = ThreadLocalRandom.current().nextInt(min, max + 1);
        return port;
    }

    private static String getString(Service service) throws IOException {
        System.out.println("enter your name");
        InputStream input1 = System.in;
        BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(input1));
        service.name = bufferedReader1.readLine();
        String name = service.name;
        return name;
    }
}

