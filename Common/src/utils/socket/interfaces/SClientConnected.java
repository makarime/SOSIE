package utils.socket.interfaces;

import utils.socket.SClient;

import java.util.EventListener;

public interface SClientConnected extends EventListener {
    void onConnected(SClient sender);
}
