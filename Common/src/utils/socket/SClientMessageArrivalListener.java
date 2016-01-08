package utils.socket;

import java.util.EventListener;

public class SClientMessageArrivalListener<T extends IMessage> implements EventListener {
    public final Class<T> clazz;
    public final IMessageArrival<T> callback;

    public SClientMessageArrivalListener(Class<T> clazz, IMessageArrival<T> callback) {
        this.clazz = clazz;
        this.callback = callback;
    }
}
