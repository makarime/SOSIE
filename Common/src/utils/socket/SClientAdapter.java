package utils.socket;

import utils.socket.interfaces.*;

public class SClientAdapter implements SClientConnected, SClientClosed, SClientDataArrival {

    @Override
    public void onConnected(SClient sender) {}

    @Override
    public void onDataArrival(SClient sender, DataArrivalEvent event) {}

    @Override
    public void onClosed(SClient sender) {}

}
