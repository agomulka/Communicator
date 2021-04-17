import java.io.IOException;

public interface AdapterInput {
    String take() throws IOException, InterruptedException;

}