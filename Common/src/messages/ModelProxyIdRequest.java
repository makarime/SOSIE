package messages;

import utils.socket.IMessage;

public class ModelProxyIdRequest implements IMessage {
    private final Class<?> clazz;
    private final int id;

    public ModelProxyIdRequest(Class<?> clazz, int id) {
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
