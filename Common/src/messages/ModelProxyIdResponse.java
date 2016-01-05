package messages;

import utils.socket.IMessage;

public class ModelProxyIdResponse implements IMessage {
    private final Object value;

    public ModelProxyIdResponse(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }
}
