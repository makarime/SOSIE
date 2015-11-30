package utils.socket;

import java.io.StringWriter;
import java.util.EventListener;

public interface SClientListener extends EventListener {
    void onConnected(SClient sender);
    void onDataArrival(SClient sender, String data, StringWriter response);
    void onClosed(SClient sender);
}