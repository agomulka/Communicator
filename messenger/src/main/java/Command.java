import java.io.IOException;

public interface Command {
    void execute(String [] myArray) throws IOException, InterruptedException;
    String description();
}
