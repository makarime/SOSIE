package Models.proxy;

import Models.DataBase;
import messages.ModelProxyIdRequest;
import messages.ModelProxyIdResponse;
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

    @Override
    public <T> T loadObjectById(Class<T> clazz, int id) {
        return clazz.cast(((ModelProxyIdResponse) socket.sendRequest(new ModelProxyIdRequest(clazz, id))).getValue());
    }
}
