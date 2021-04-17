public class ReceiverAdapterOut implements AdapterOutput {
    @Override
    public void put(String line) {
        System.out.println(line);
    }

}
