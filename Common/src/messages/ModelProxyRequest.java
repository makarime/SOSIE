package messages;

import utils.socket.IMessage;

public class ModelProxyRequest implements IMessage {
    private final IMessage msg;

    public ModelProxyRequest(IMessage msg) {
        this.msg = msg;
    }

    public IMessage getMsg() {
        return msg;
    }
}
