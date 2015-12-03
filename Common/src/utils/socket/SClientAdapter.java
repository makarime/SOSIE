package utils.socket;

import java.io.ByteArrayOutputStream;

public class SClientAdapter implements SClientListener {

    @Override
    public void onConnected(SClient sender) {}

    @Override
    public void onDataArrival(SClient sender, IMessage msg, ByteArrayOutputStream response) {}

    @Override
    public void onClosed(SClient sender) {}

}
