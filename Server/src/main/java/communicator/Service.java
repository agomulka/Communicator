package communicator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Service{
    List<String> list;
    String name;

    public Service() {
        list = new ArrayList<>();
    }

    public List<String> getList() {
        return list;
    }

    public void add(String name){
        list.add(name);
    }

    public void removePerson(String name){
        int n = list.indexOf(name);
        list.remove(n);

    }

    public void printList(){
        System.out.println("List available:");
        if(list.isEmpty()){
            System.out.println("empty list!");
        }else{
            for(String s : list){
                System.out.println(s);
            }
        }
    }

    public String name(){
        return name;
    }

    public Integer getPort(){
        Integer min = 8075;
        Integer max = 9090;
        int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
        return randomNum;
    }
}
