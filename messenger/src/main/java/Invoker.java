import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Invoker {
    private Map<String, Command> commands;
    String name;
    private Integer port;

    public Invoker(String name, Integer port, Command... command) {
        this.port = port;
        this.commands = new HashMap<>();
        this.name = name;
        for(Command c : command){
            commands.put(c.description(),c);
        }

    }

    public void invoke(String line) throws IOException, InterruptedException {
        String[] array = line.split(" +");
        Command commandToExecute = commands.get(array[0]);
        commandToExecute.execute(array);
    }

    public void startMenu() throws IOException {
        System.out.println("Hello "+name+" "+port+" what do you want to do?");
        for(String key : commands.keySet()){
            Command value =commands.get(key);
            System.out.println(value.description());

        }
    }

}