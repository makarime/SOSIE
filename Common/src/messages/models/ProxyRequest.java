package messages.models;

import utils.socket.IMessage;

public class ProxyRequest implements IMessage {
    private final IMessage msg;

    public ProxyRequest(IMessage msg) {
        this.msg = msg;
    }

    public IMessage getMsg() {
        return msg;
    }
}
