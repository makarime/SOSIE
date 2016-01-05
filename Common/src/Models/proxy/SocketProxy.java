package Models.proxy;

import messages.models.*;
import utils.socket.IMessage;
import utils.socket.SClient;

import java.util.List;

public class SocketProxy implements IProxy {
    private final SClient socket;

    public SocketProxy(SClient socket) {
        this.socket = socket;
    }

    @Override
    public IMessage load(IMessage msg) {
        return ((ProxyResponse) socket.sendRequest(new ProxyRequest(msg))).getValue();
    }

    @Override
    public <T> T loadObjectById(Class<T> clazz, int id) {
        return clazz.cast(((ProxyIdResponse) socket.sendRequest(new ProxyIdRequest(clazz, id))).getValue());
    }

    @Override
    public <T> List<T> loadObjectByReverseId(Class<T> target, Class<?> source, int id) {
        //TODO: A Voir pour eviter le unchecked
        return ((ProxyReverseIdResponse<T>) socket.sendRequest(new ProxyReverseIdRequest(target, source, id))).getValue();
    }
}
