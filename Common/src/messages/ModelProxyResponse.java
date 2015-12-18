package messages;

import utils.socket.IMessage;

public class ModelProxyResponse implements IMessage {
    private final Object value;

    public ModelProxyResponse(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }
}
