package Models.proxy;

import Models.DataBase;
import messages.ModelProxyRequest;
import messages.ModelProxyResponse;
import utils.socket.IMessage;
import utils.socket.SClient;

public class SocketProxy implements IProxy {
    private final SClient socket;

    public SocketProxy(SClient socket) {
        this.socket = socket;
    }

    @Override
    public IMessage load(IMessage msg) {
        return ((ModelProxyResponse) socket.sendRequest(new ModelProxyRequest(msg))).getValue();
    }
}
