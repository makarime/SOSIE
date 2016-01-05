package messages.models;

import utils.socket.IMessage;

public class ProxyIdResponse implements IMessage {
    private final Object value;

    public ProxyIdResponse(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }
}
