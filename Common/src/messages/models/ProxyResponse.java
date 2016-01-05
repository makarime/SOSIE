package messages.models;

import utils.socket.IMessage;

public class ProxyResponse implements IMessage {
    private final IMessage value;

    public ProxyResponse(IMessage value) {
        this.value = value;
    }

    public IMessage getValue() {
        return value;
    }
}
