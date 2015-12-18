package messages;

import utils.socket.IMessage;

public class ModelProxyResponse implements IMessage {
    private final IMessage value;

    public ModelProxyResponse(IMessage value) {
        this.value = value;
    }

    public IMessage getValue() {
        return value;
    }
}
