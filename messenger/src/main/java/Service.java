import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Service {
    List<String> list;
    String name;
    AtomicBoolean barrier = new AtomicBoolean();
    AtomicBoolean b2 = new AtomicBoolean();

    public Service() {
        list = new ArrayList<>();
    }

    public String name(){
        return name;
    }

}
