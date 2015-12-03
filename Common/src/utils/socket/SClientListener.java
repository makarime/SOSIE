package utils.socket;

import java.io.ByteArrayOutputStream;
import java.util.EventListener;

public interface SClientListener extends EventListener {
    void onConnected(SClient sender);
    void onDataArrival(SClient sender, IMessage msg, ByteArrayOutputStream response);
    void onClosed(SClient sender);
}