package Models.proxy;

import Models.DataBase;
import messages.*;
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
        return ((ModelProxyResponse) socket.sendRequest(new ModelProxyRequest(msg))).getValue();
    }

    @Override
    public <T> T loadObjectById(Class<T> clazz, int id) {
        return clazz.cast(((ModelProxyIdResponse) socket.sendRequest(new ModelProxyIdRequest(clazz, id))).getValue());
    }

    @Override
    public <T> List<T> loadObjectByReverseId(Class<T> target, Class<?> source, int id) {
        //TODO: A Voir pour eviter le unchecked
        return (List<T>) ((ModelProxyReverseIdResponse) socket.sendRequest(new ModelProxyReverseIdRequest(target, source, id))).getValue();
    }
}
