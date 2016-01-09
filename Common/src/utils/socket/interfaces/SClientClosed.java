package utils.socket.interfaces;

import utils.socket.SClient;

import java.util.EventListener;

public interface SClientClosed extends EventListener {
    void onClosed(SClient sender);
}
