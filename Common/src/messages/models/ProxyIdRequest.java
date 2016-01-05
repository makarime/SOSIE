package messages.models;

import utils.socket.IMessage;

public class ProxyIdRequest implements IMessage {
    private final Class<?> clazz;
    private final int id;

    public ProxyIdRequest(Class<?> clazz, int id) {
        this.clazz = clazz;
        this.id = id;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public int getId() {
        return id;
    }
}
