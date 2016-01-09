package utils.socket.interfaces;

import utils.socket.DataArrivalEvent;
import utils.socket.SClient;

import java.util.EventListener;

public interface SClientDataArrival extends EventListener {
    void onDataArrival(SClient sender, DataArrivalEvent event);
}
