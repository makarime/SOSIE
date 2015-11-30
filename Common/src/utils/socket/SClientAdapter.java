package utils.socket;

import java.io.StringWriter;

public class SClientAdapter implements SClientListener {

    @Override
    public void onConnected(SClient sender) {}

    @Override
    public void onDataArrival(SClient sender, String data, StringWriter response) {}

    @Override
    public void onClosed(SClient sender) {}

}
