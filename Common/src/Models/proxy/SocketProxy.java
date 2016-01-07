package Models.proxy;

import messages.models.*;
import utils.socket.IMessage;
import utils.socket.SClient;

import java.util.HashMap;
import java.util.List;

public class SocketProxy implements IProxy {
    private final SClient socket;
    private final HashMap<String, Object> cache = new HashMap<>();

    public SocketProxy(SClient socket) {
        this.socket = socket;
    }

    @Override
    public IMessage load(IMessage msg) {
        return ((ProxyResponse) socket.sendRequest(new ProxyRequest(msg))).getValue();
    }

    @Override
    public <T> T loadObjectById(Class<T> clazz, int id) {
        if(cache.containsKey(clazz.getName() + ";" + id))
            return clazz.cast(cache.get(clazz.getName() + ";" + id));

        Object response = ((ProxyIdResponse) socket.sendRequest(new ProxyIdRequest(clazz, id))).getValue();
        cache.put(clazz.getName() + ";" + id, response);
        return clazz.cast(response);
    }

    @Override
    public <T> List<T> loadObjectByReverseId(Class<T> target, Class<?> source, int id) {
        //TODO: A Voir pour eviter le unchecked
        if(cache.containsKey(target.getName() + ";" + source.getName() + ";" + id))
            return (List<T>) cache.get(target.getName() + ";" + source.getName() + ";" + id);

        Object response = ((ProxyReverseIdResponse) socket.sendRequest(new ProxyReverseIdRequest(target, source, id))).getValue();
        cache.put(target.getName() + ";" + source.getName() + ";" + id, response);
        return (List<T>) response;
    }
}
