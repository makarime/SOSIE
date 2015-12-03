package utils.socket;

public class SClientAdapter implements SClientListener {

    @Override
    public void onConnected(SClient sender) {}

    @Override
    public void onDataArrival(SClient sender, DataArrivalEvent event) {}

    @Override
    public void onClosed(SClient sender) {}

}
