package utils.socket;

import java.util.EventListener;

public interface SClientListener extends EventListener {
    void onConnected(SClient sender);
    void onDataArrival(SClient sender, DataArrivalEvent event);
    void onClosed(SClient sender);
}