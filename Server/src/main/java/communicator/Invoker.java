package communicator;

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
//        commands.put(command1.description(), command1);
//        commands.put(command2.description(), command2);
        this.name = name;
//        commands.put(command3.description(), command3);
//        commands.put(command4.description(), command4);
        for(Command c : command){
            commands.put(c.description(),c);
        }

    }

    public void invoke(String line) throws IOException {
        String[] array = line.split(" +");
        Command commandToExecute = commands.get(array[0]);
        commandToExecute.execute(array);
    }

    public void startMenu() throws IOException {
        System.out.println("Hello "+name+" "+port+" what do you want to do?");
        int i =1;
        for(String key : commands.keySet()){
            Command value =commands.get(key);
            System.out.println(i+" "+value.description());
            i++;
        }
    }

}
